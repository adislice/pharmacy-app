package com.uty.apotekku

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class BannerAdapter(private val list: ArrayList<BannerModel>):RecyclerView.Adapter<BannerAdapter.Holder>() {
    class Holder (view: View): RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.banner_textview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.banner_item, parent, false)
        return Holder(v)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val img: String = list[position].image
        val link: String = list[position].link

        holder.title.text = list[position].title
        Glide.with(holder.itemView.context)
            .load(img)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.color.white)
            .into(holder.itemView.findViewById(R.id.banner_image_view))
        holder.itemView.setOnClickListener {
            val ctx = holder.itemView.context
            val uri: Uri = Uri.parse(link)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            ctx.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = list.size
}