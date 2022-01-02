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
import com.uty.apotekku.Model.DaftarObatDataModel
import com.uty.apotekku.R
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

open class DaftarObatAdapter(private val list: ArrayList<DaftarObatDataModel>, open var limit: Int = 0): RecyclerView.Adapter<DaftarObatAdapter.Holder>() {
    class Holder (view: View): RecyclerView.ViewHolder(view){
        val obatname: TextView = view.findViewById(R.id.daftar_nama_produk)
        val obatharga: TextView = view.findViewById(R.id.daftar_harga_produk_old)
        val obatdiskon: TextView = view.findViewById(R.id.daftar_diskon_produk)
        val obathargafinal: TextView = view.findViewById(R.id.daftar_harga_produk_final)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list_daftar_produk, parent, false)
        return Holder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {

        val img: String = list[position].gambar
        val hargalama = list[position].harga?.toDouble()

        if (list[position].diskon == 0){
            holder.obatname.text = list[position].nama_obat
            holder.obatdiskon.text = ""
            holder.obatharga.text = ""
            holder.obathargafinal.text = hargalama?.let { rupiah(it) }
        } else {
            val hitungdiskon: Double = (list[position].diskon!!.toDouble() / 100) * list[position].harga!!
            val hargafinal = list[position].harga!! - hitungdiskon

            holder.obatname.text = list[position].nama_obat
            holder.obatdiskon.text = list[position].diskon.toString() + "%"
            holder.obatharga.text = hargalama?.let { rupiah(it) }
            holder.obathargafinal.text = rupiah(hargafinal)
        }

        Glide.with(holder.itemView.context)
            .load(img)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.color.white)
            .into(holder.itemView.findViewById(R.id.daftar_gambar_produk))
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

private fun rupiah(number: Double): String {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    return numberFormat.format(number)
}