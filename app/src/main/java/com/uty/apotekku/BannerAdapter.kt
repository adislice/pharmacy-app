package com.uty.apotekku

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class BannerAdapter(private val list: ArrayList<BannerModel>):
    RecyclerView.Adapter<BannerAdapter.Holder>() {
    class Holder (view: View): RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.banner_textview)
        val image: ImageView = view.findViewById(R.id.banner_image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.banner_item, parent, false)
        return Holder(v)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text = list[position].title
        holder.image.setImageResource(list[position].image)

    }

    override fun getItemCount(): Int = list.size
}