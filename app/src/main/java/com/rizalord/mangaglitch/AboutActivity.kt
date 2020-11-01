package com.rizalord.mangaglitch

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val gh : ImageView = findViewById(R.id.github)
        val li : ImageView = findViewById(R.id.linkedin)
        val tw : ImageView = findViewById(R.id.twitter)
        val btnBack: ImageView = findViewById(R.id.btn_back)

        gh.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/rizalord"))
            startActivity(intent)
        }

        li.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/rizalord"))
            startActivity(intent)
        }

        tw.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/rizalord_"))
            startActivity(intent)
        }

        btnBack.setOnClickListener { finish() }

    }
}