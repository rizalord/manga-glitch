package com.rizalord.mangaglitch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rizalord.mangaglitch.adapters.CardAdapter
import com.rizalord.mangaglitch.adapters.CarouselAdapter
import com.rizalord.mangaglitch.config.NetworkConfig
import com.rizalord.mangaglitch.data.PopularComics
import com.rizalord.mangaglitch.data.ResultsItem
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var rv_carousel: RecyclerView
    private lateinit var rv_cardlist: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setCarousel()
        setCardList()
    }

    private fun setCardList() {
        NetworkConfig()
                .getService()
                .getNewest()
                .enqueue(object: retrofit2.Callback<PopularComics> {
                    override fun onFailure(call: Call<PopularComics>, t: Throwable) {}

                    override fun onResponse(call: Call<PopularComics>, response: Response<PopularComics>) {
                        rv_cardlist = findViewById<RecyclerView>(R.id.rv_newest)
                        rv_cardlist.setHasFixedSize(true)
                        rv_cardlist.isNestedScrollingEnabled = false
                        rv_cardlist.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL,false)
                        rv_cardlist.adapter = CardAdapter(response.body()?.results?.subList(0,10) as List<ResultsItem>)
                    }

                })
    }

    private fun setCarousel(){

        NetworkConfig()
            .getService()
            .getPopular()
            .enqueue(object : retrofit2.Callback<PopularComics> {
                override fun onFailure(call: Call<PopularComics>, t: Throwable) {

                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                    Log.d("ERRORMESSAGE", t.localizedMessage)

                }

                override fun onResponse(
                    call: Call<PopularComics>,
                    response: Response<PopularComics>
                ) {
                    rv_carousel = findViewById(R.id.carousel_recyclerview)
                    rv_carousel.setHasFixedSize(true)
                    rv_carousel.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                    rv_carousel.adapter = CarouselAdapter(response.body()?.results?.subList(0, 8))
                }

            })

    }
}