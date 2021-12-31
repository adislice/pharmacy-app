package com.uty.apotekku

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CheckoutHitungAdapter(private val list: ArrayList<CheckoutHitungModel>):RecyclerView.Adapter<CheckoutHitungAdapter.Holder>() {
    class Holder (view: View): RecyclerView.ViewHolder(view){
        val detailJudul: TextView = view.findViewById(R.id.checkout_hitung_detail_title)
        val detailHitung: TextView = view.findViewById(R.id.checkout_hitung_detail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_checkout_hitungan, parent, false)
        return Holder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.detailJudul.text = list[position].detailJudul
        holder.detailHitung.text = "Rp. " + list[position].detailHitung.toString()
    }

    override fun getItemCount(): Int = list.size
}