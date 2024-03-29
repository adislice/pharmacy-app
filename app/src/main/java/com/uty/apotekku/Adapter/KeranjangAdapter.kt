package com.uty.apotekku.Adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.uty.apotekku.DetailProdukActivity
import com.uty.apotekku.Model.KeranjangDataModel
import com.uty.apotekku.R
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

open class KeranjangAdapter(private val list: ArrayList<KeranjangDataModel>, open var limit: Int = 0, open var id_user: Int = 0): RecyclerView.Adapter<KeranjangAdapter.Holder>() {
    class Holder (view: View): RecyclerView.ViewHolder(view){
        val krjcont: LinearLayout = view.findViewById(R.id.krj_container_list)
        val krjnama: TextView = view.findViewById(R.id.tv_krj_nama_produk)
        val krjhargafinal: TextView = view.findViewById(R.id.tv_krj_harga_produk)
        val krjjumlahproduk: EditText = view.findViewById(R.id.krj_qty)
        val krjbtntambah: ImageButton = view.findViewById(R.id.krj_qty_tambah)
        val krjbtnkurang: ImageButton = view.findViewById(R.id.krj_qty_kurang)
        val krjcb: CheckBox = view.findViewById(R.id.krjcb)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_keranjang, parent, false)
        return Holder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {

        val img: String = list[position].gambar
        val harga = list[position].harga_setelah_diskon.toDouble()
        val qty = list[position].qty

        holder.krjnama.text = list[position].nama_produk
        holder.krjhargafinal.text = rupiah(harga)
        holder.krjjumlahproduk.setText(qty.toString())

        Glide.with(holder.itemView.context)
            .load(img)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.color.white)
            .into(holder.itemView.findViewById(R.id.img_krj_gambar_produk))

        holder.krjcont.setOnClickListener {
            val ctx = holder.itemView.context
            val intent = Intent(ctx, DetailProdukActivity::class.java)
            if(list[position].kategori_produk == "obat"){
                intent.putExtra("id_obat", list[position].id_produk)
            } else if (list[position].kategori_produk == "alkes"){
                intent.putExtra("id_alkes", list[position].id_produk)
            }
            intent.putExtra("id_user", id_user)
            intent.putExtra("kategori", list[position].kategori_produk)
            ctx.startActivity(intent)
        }

        holder.krjbtnkurang.setOnClickListener{
            val ctx = holder.itemView.context
            if (qty > 1) {
                var qty = holder.krjjumlahproduk.text.toString().toInt()
                qty--
                holder.krjjumlahproduk.setText(qty.toString())
                Toast.makeText(ctx, "kuantitas : " + holder.krjjumlahproduk.text.toString().toInt(), Toast.LENGTH_SHORT).show()
            }
        }

        holder.krjbtntambah.setOnClickListener{
            val ctx = holder.itemView.context
            var qty = holder.krjjumlahproduk.text.toString().toInt()
            qty++
            holder.krjjumlahproduk.setText(qty.toString())
            Toast.makeText(ctx, "kuantitas : " + holder.krjjumlahproduk.text.toString().toInt(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int =  if(limit > 0 && list.size >= limit) limit else list.size
}

private fun rupiah(number: Double): String {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    numberFormat.maximumFractionDigits = 0
    return numberFormat.format(number)
}