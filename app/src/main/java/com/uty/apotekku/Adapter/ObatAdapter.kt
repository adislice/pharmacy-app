package com.uty.apotekku.Adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.uty.apotekku.DetailProdukActivity
import com.uty.apotekku.Model.DaftarObatDataModel
import com.uty.apotekku.Model.ObatDataModel
import com.uty.apotekku.R

open class ObatAdapter(private val list: ArrayList<ObatDataModel>, open var limit: Int = 0): RecyclerView.Adapter<ObatAdapter.Holder>() {
    class Holder (view: View): RecyclerView.ViewHolder(view){
        val obat_name: TextView = view.findViewById(R.id.obat_name)
        val obat_image: ImageView = view.findViewById(R.id.obat_image)
        val obat_jenis: TextView = view.findViewById(R.id.obat_jenis)
        val obat_harga: TextView = view.findViewById(R.id.obat_harga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.obat_item_list, parent, false)
        return Holder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        var img: String = list[position].gambar
        holder.obat_name.text = list[position].nama_obat
//        holder.obat_image.setImageResource(list[position].gambar)
        holder.obat_jenis.text = list[position].jenis_obat
        holder.obat_harga.text = "Rp. " + list[position].harga
//        Toast.makeText(holder.itemView.context,img, Toast.LENGTH_SHORT).show()
        Glide.with(holder.itemView.context)
            .load(img)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.color.white)
            .into(holder.itemView.findViewById(R.id.obat_image))
        holder.itemView.setOnClickListener {
            val ctx = holder.itemView.context
            val intent = Intent(ctx, DetailProdukActivity::class.java)
            intent.putExtra("id_obat", list[position].id_obat)
            intent.putExtra("kategori", "obat")
            ctx.startActivity(intent)
        }
    }

    override fun getItemCount(): Int =  if(limit > 0 && list.size >= limit) limit else list.size
}