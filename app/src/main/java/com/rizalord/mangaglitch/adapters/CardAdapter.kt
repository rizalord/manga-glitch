package com.rizalord.mangaglitch.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rizalord.mangaglitch.DetailActivity
import com.rizalord.mangaglitch.R
import com.rizalord.mangaglitch.data.PopularComics
import com.rizalord.mangaglitch.data.ResultsItem

class CardAdapter(val listData: List<ResultsItem>, val context: Context): RecyclerView.Adapter<CardAdapter.CardViewHolder>() {
    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.img_view)
        var title: TextView = itemView.findViewById(R.id.title_item)
        var sinopsis: TextView = itemView.findViewById(R.id.sinopsis_item)
        var typeManga: TextView = itemView.findViewById(R.id.type_manga)
        var typeOneshot: TextView = itemView.findViewById(R.id.type_oneshot)
        var score: TextView = itemView.findViewById(R.id.score)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_card_home, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
                .load(listData[position].imageUrl)
                .apply(RequestOptions().placeholder(R.color.textsinopsis).error(R.color.textsinopsis))
                .into(holder.image)

        holder.title.text = listData[position].title
        holder.sinopsis.text = if(listData[position].synopsis?.isBlank()!!) "-" else listData[position].synopsis

        holder.typeManga.visibility = if (listData[position].type == "Manga") View.VISIBLE else View.GONE
        holder.typeOneshot.visibility = if (listData[position].type != "Manga") View.VISIBLE else View.GONE

        holder.score.text = listData[position].score.toString()

        holder.itemView.setOnClickListener {
            val intent: Intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("id", listData?.get(position)!!.malId)
            context.startActivity(intent)
        }
    }
}