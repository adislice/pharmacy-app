package com.uty.apotekku

import android.annotation.SuppressLint
<<<<<<< Updated upstream
import android.support.v7.widget.RecyclerView
=======
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
>>>>>>> Stashed changes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

open class ObatAdapter(private val list: ArrayList<ObatModel>, open var limit: Int = 0): RecyclerView.Adapter<ObatAdapter.Holder>() {
    class Holder (view: View): RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.obat_name)
        val image: ImageView = view.findViewById(R.id.obat_image)
        val jenis: TextView = view.findViewById(R.id.obat_jenis)
        val harga: TextView = view.findViewById(R.id.obat_harga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.obat_item_list, parent, false)
        return Holder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text = list[position].name
        holder.image.setImageResource(list[position].image)
        holder.jenis.text = list[position].jenis
        holder.harga.text = "Rp. " + list[position].harga
    }

    override fun getItemCount(): Int = if(limit > 0) limit else list.size
}