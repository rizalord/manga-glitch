package com.rizalord.mangaglitch

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
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
        setAboutListener()
    }

    private fun setAboutListener(){
        val about: LinearLayout = findViewById(R.id.btn_about)
        about.setOnClickListener {
            val intent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setCardList() {
        NetworkConfig()
                .getService()
                .getNewest()
                .enqueue(object: retrofit2.Callback<PopularComics> {
                    override fun onFailure(call: Call<PopularComics>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "There's problem with your internet connection, please try again later.", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<PopularComics>, response: Response<PopularComics>) {
                        rv_cardlist = findViewById<RecyclerView>(R.id.rv_newest)
                        rv_cardlist.setHasFixedSize(true)
                        rv_cardlist.isNestedScrollingEnabled = false
                        rv_cardlist.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL,false)
                        rv_cardlist.adapter = CardAdapter(response.body()?.results?.subList(0,10) as List<ResultsItem>, this@MainActivity)

                        val loading: LinearLayout = findViewById(R.id.loading2)
                        loading.visibility = View.INVISIBLE
                        loading.layoutParams.height = 0
                        rv_cardlist.visibility = View.VISIBLE
                    }

                })
    }

    private fun setCarousel(){

        NetworkConfig()
            .getService()
            .getPopular()
            .enqueue(object : retrofit2.Callback<PopularComics> {
                override fun onFailure(call: Call<PopularComics>, t: Throwable) {

                    Toast.makeText(this@MainActivity, "There's problem with your internet connection, please try again later.", Toast.LENGTH_SHORT).show()

                }

                override fun onResponse(
                    call: Call<PopularComics>,
                    response: Response<PopularComics>
                ) {
                    rv_carousel = findViewById(R.id.carousel_recyclerview)
                    rv_carousel.setHasFixedSize(true)
                    rv_carousel.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                    rv_carousel.adapter = CarouselAdapter(response.body()?.results?.subList(0, 8), this@MainActivity)

                    val loading: LinearLayout = findViewById(R.id.loading1)
                    loading.visibility = View.INVISIBLE
                    loading.layoutParams.height = 0
                    rv_carousel.visibility = View.VISIBLE
                }

            })

    }
}