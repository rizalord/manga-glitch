package com.rizalord.mangaglitch.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.marginRight
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rizalord.mangaglitch.DetailActivity
import com.rizalord.mangaglitch.R
import com.rizalord.mangaglitch.data.ResultsItem

class CarouselAdapter(val listData: List<ResultsItem?>?, val context: Context): RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {
    class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.image_item)
        var title: TextView = itemView.findViewById(R.id.comic_title)
        var score: TextView = itemView.findViewById(R.id.score)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel_home, parent , false)
        return CarouselViewHolder(view)
    }

    override fun getItemCount(): Int = listData?.size ?: 0

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {



        Glide.with(holder.itemView.context)
            .load(listData?.get(position)?.imageUrl)
            .apply(RequestOptions().placeholder(R.color.textsinopsis).error(R.color.textsinopsis))
            .into(holder.imageView)

        holder.title.text = listData?.get(position)!!.title
        holder.score.text = listData?.get(position)!!.score.toString()

        holder.itemView.setOnClickListener {
            val intent: Intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("id", listData?.get(position)!!.malId)
            context.startActivity(intent)
        }


    }
}