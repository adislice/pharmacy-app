package com.uty.apotekku.Adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.uty.apotekku.DetailProdukActivity
import com.uty.apotekku.Model.AlkesDataModel
import com.uty.apotekku.R
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

open class AlkesAdapter(private val list: ArrayList<AlkesDataModel>, open var limit: Int = 0): RecyclerView.Adapter<AlkesAdapter.Holder>() {
    class Holder (view: View): RecyclerView.ViewHolder(view){
        val alkes_name: TextView = view.findViewById(R.id.obat_name)
        val alkes_jenis: TextView = view.findViewById(R.id.obat_jenis)
        val alkes_harga: TextView = view.findViewById(R.id.obat_harga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.obat_item_list, parent, false)
        return Holder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val img: String = list[position].gambar
        holder.alkes_name.text = list[position].nama_alkes
        holder.alkes_jenis.visibility = View.GONE
        holder.alkes_harga.text = list[position].harga?.let { rupiah(it.toDouble()) }
        Glide.with(holder.itemView.context)
            .load(img)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.color.white)
            .into(holder.itemView.findViewById(R.id.obat_image))
        holder.itemView.setOnClickListener {
            val ctx = holder.itemView.context
            val intent = Intent(ctx, DetailProdukActivity::class.java)
            intent.putExtra("id_obat", list[position].id_alkes)
            intent.putExtra("kategori", "obat")
            ctx.startActivity(intent)
        }
    }

    override fun getItemCount(): Int =  if(limit > 0 && list.size >= limit) limit else list.size
}

private fun rupiah(number: Double): String {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    return numberFormat.format(number)
}