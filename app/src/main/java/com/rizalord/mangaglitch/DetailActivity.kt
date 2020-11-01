package com.rizalord.mangaglitch

import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.rizalord.mangaglitch.config.NetworkConfig
import com.rizalord.mangaglitch.data.DetailComic
import retrofit2.Call
import retrofit2.Response
import kotlin.properties.Delegates

class DetailActivity : AppCompatActivity() {

    private var id by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        id = intent.getIntExtra("id", -1)

        NetworkConfig()
                .getService()
                .getDetail(id)
                .enqueue(object: retrofit2.Callback<DetailComic> {
                    override fun onResponse(call: Call<DetailComic>, response: Response<DetailComic>) {
                        showContent()
                        response.body()?.let { setContent(it) }
                    }

                    override fun onFailure(call: Call<DetailComic>, t: Throwable) {
                        Toast.makeText(this@DetailActivity, "FAILED TO LOAD DATA", Toast.LENGTH_SHORT).show()
                    }

                })
    }

    fun showContent () {

        val contentView: ScrollView = findViewById(R.id.content_view)
        val loadingView: LinearLayout = findViewById(R.id.loading_view)

        loadingView.visibility = View.GONE
        contentView.visibility = View.VISIBLE
    }

    fun setContent(comic: DetailComic) {
        val image: ImageView = findViewById(R.id.img_cover)
        val title: TextView = findViewById(R.id.title)
        val author: TextView = findViewById(R.id.author)
        val score: TextView = findViewById(R.id.score)
        val genre1: TextView = findViewById(R.id.genre_1)
        val genre2: TextView = findViewById(R.id.genre_2)
        val btnMoreDetail: TextView = findViewById(R.id.btn_more_detail)
        val btnShare: ImageView = findViewById(R.id.btn_share)
        val btnBack: ImageView = findViewById(R.id.btn_back)
        val sinopsis: TextView = findViewById(R.id.sinopsis)
        val background: TextView = findViewById(R.id.background_text)
        val dTitle: TextView = findViewById(R.id.detail_title)
        val dTitleEnglish: TextView = findViewById(R.id.detail_title_english)
        val dTitleJapanese: TextView = findViewById(R.id.detail_title_japanese)
        val dStatus: TextView = findViewById(R.id.detail_status)
        val dType: TextView = findViewById(R.id.detail_type)
        val dPublished: TextView = findViewById(R.id.detail_published)

        title.text = comic.title
        author.text = if (comic.authors?.size!! > 0) comic.authors[0]?.name else ""
        if (comic.authors?.size == 0 ) author.visibility = View.GONE
        score.text = comic.score.toString()


        when (comic.genres?.size!!) {

            0 -> {
                genre1.visibility = View.GONE
                genre2.visibility = View.GONE
            }

            1 -> {
                genre1.text = comic.genres[0]?.name
                genre2.visibility = View.GONE
            }

            else -> {
                genre1.text = comic.genres[0]?.name
                genre2.text = comic.genres[1]?.name
            }
        }

        sinopsis.text = if (comic.synopsis == null) "-" else comic.synopsis
        background.text = if (comic.background == null) "-" else comic.background
        dTitle.text = comic.title
        dTitleEnglish.text = comic.titleEnglish
        dTitleJapanese.text = comic.titleJapanese
        dStatus.text = comic.status
        dType.text = comic.type
        dPublished.text = comic.published!!.string

        Glide
                .with(this)
                .load(comic.imageUrl)
                .into(image)

        btnBack.setOnClickListener { finish() }
        btnShare.setOnClickListener {
            val intent: Intent = Intent()
            intent.putExtra(Intent.EXTRA_TEXT, "Hey, this manga is so cool, let's check it out now. " + comic.url)
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent, "Share to:"))
        }

        btnMoreDetail.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(comic.url))
            startActivity(intent)
        }
    }
}