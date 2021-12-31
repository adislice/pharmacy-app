package com.uty.apotekku

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class CheckoutAdapter(private val list: ArrayList<CheckoutModel>):RecyclerView.Adapter<CheckoutAdapter.Holder>() {
    class Holder (view: View): RecyclerView.ViewHolder(view){
        val produknama: TextView = view.findViewById(R.id.checkout_nama_produk)
        val produkharga: TextView = view.findViewById(R.id.checkout_harga_produk)
        val produkqty: TextView = view.findViewById(R.id.checkout_qty_produk)
        val produkgambar: ImageView = view.findViewById(R.id.checkout_gambar_produk)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_produk_checkout, parent, false)
        return Holder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.produknama.text = list[position].nama_produk
        holder.produkharga.text = list[position].harga_produk.toString()
        holder.produkqty.text = list[position].qty_produk.toString() + "x"
        holder.produkgambar.setImageResource(list[position].gambar_produk)
    }

    override fun getItemCount(): Int = list.size
}