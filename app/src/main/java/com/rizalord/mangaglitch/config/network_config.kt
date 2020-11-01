package com.rizalord.mangaglitch.config

import com.rizalord.mangaglitch.data.DetailComic
import com.rizalord.mangaglitch.data.PopularComics
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class NetworkConfig {

    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return okHttpClient
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v3/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() = getRetrofit().create(Comics::class.java)
}

interface Comics {
    @GET("search/manga?order_by=members&page=1")
    fun getPopular(): Call<PopularComics>

    @GET("search/manga?order_by=start_date&sort=desc&page=1")
    fun getNewest(): Call<PopularComics>

    @GET("manga/{id}")
    fun getDetail(@Path("id") id: Int ): Call<DetailComic>
}