package com.uty.apotekku

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

class KeranjangAdapter(private val list: ArrayList<KeranjangModel>): RecyclerView.Adapter<KeranjangAdapter.Holder>() {

    class Holder (view: View): RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.tv_nama)
        val jml: EditText = view.findViewById(R.id.et_jumlah)
        val price: TextView = view.findViewById(R.id.tv_harga)
        var kuantitas = jml.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_keranjang, parent, false)
        return Holder(v)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text = list[position].nama
        holder.price.text = list[position].harga.toString()
        holder.kuantitas = list[position].qty.toString()
//        holder.image.setImageResource(list[position].image)
    }

    override fun getItemCount(): Int = list.size
}
