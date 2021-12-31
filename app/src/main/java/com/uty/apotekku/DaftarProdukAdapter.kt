package com.uty.apotekku

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class DaftarProdukAdapter(private val list: ArrayList<DaftarProdukModel>):RecyclerView.Adapter<DaftarProdukAdapter.Holder>() {
    class Holder (view: View): RecyclerView.ViewHolder(view){
        val produkjudul: TextView = view.findViewById(R.id.daftar_nama_produk)
        val produkharga: TextView = view.findViewById(R.id.daftar_harga_produk_old)
        val produkdiskon: TextView = view.findViewById(R.id.daftar_diskon_produk)
        val produkgambar: ImageView = view.findViewById(R.id.daftar_gambar_produk)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list_daftar_produk, parent, false)
        return Holder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.produkjudul.text = list[position].namaproduk
        holder.produkdiskon.text = list[position].diskonproduk.toString() + "%"
        holder.produkharga.text = "Rp. " + list[position].hargaproduk.toString()
        holder.produkgambar.setImageResource(list[position].gambarproduk)
    }
    override fun getItemCount(): Int = list.size
}