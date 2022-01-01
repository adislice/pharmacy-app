package com.uty.apotekku

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.TextViewCompat
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.uty.apotekku.API.APIRequestData
import com.uty.apotekku.API.RetroServer
import com.uty.apotekku.Model.ObatResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*

class DetailProdukActivity : AppCompatActivity() {

    private lateinit var etqty: EditText
    private lateinit var iv_produkGambar: ImageView
    private lateinit var tv_produkNama: TextView
    private lateinit var tv_produkHargaAsli: TextView
    private lateinit var tv_produkHargaFinal: TextView
    private lateinit var tv_produkJenis: TextView
    private lateinit var tv_produkDiskon: TextView
    private lateinit var tv_produkDeskripsi: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_produk)

        val kategori = intent.getStringExtra("kategori")
        var id_produk: Int = 0
        if (kategori == "obat") {
            id_produk = intent.getIntExtra("id_obat", 1)
        }

        val btnback: ImageButton = findViewById(R.id.produk_back)
        val btnchart: ImageButton = findViewById(R.id.produk_keranjang)
        val btntambah: ImageButton = findViewById(R.id.produk_qty_tambah)
        val btnkurang: ImageButton = findViewById(R.id.produk_qty_kurang)

        iv_produkGambar = findViewById(R.id.produk_gambar)
        tv_produkNama = findViewById(R.id.produk_nama)
        tv_produkHargaAsli = findViewById(R.id.produk_harga_asli)
        tv_produkHargaFinal = findViewById(R.id.produk_harga_final)
        tv_produkDiskon = findViewById(R.id.produk_diskon)
        tv_produkJenis = findViewById(R.id.produk_jenis)
        tv_produkDeskripsi = findViewById(R.id.produk_deskripsi)

        etqty = findViewById(R.id.produk_qty)
        var qty: Int = etqty.text.toString().toInt()

        btnback.setOnClickListener {finish()}
        btnchart.setOnClickListener {bukaKeranjang()}
        btntambah.setOnClickListener {qty += 1}
        btnkurang.setOnClickListener {qty -= 1}


        fun retrieveDetailObat(id_produk: Int){
            val ardData: APIRequestData = RetroServer.konekRetrofit()!!.create(APIRequestData::class.java)
            val tampilObat: Call<ObatResponseModel> = ardData.getDetailObat("get_obat", id_produk)
            tampilObat.enqueue(object: Callback<ObatResponseModel>{
                override fun onResponse(
                    call: Call<ObatResponseModel>,
                    response: Response<ObatResponseModel>
                ) {
                    var obat = response.body()!!.result
                    val nama_obat = obat.get(0).nama_obat
                    val harga_obat = obat.get(0).harga
                    val jenis_obat = obat.get(0).jenis_obat
                    val deskripsi_obat = obat.get(0).deskripsi
                    val gambar_obat = obat.get(0).gambar
                    val diskon_obat = obat.get(0).diskon


                    if (diskon_obat == 0){
                        tv_produkDiskon.visibility = View.GONE
                        tv_produkHargaFinal.text = harga_obat.toString()
                        tv_produkHargaAsli.visibility = View.GONE
                    } else {
                        val hitung_diskon: Double = (diskon_obat!!.toDouble() / 100) * harga_obat!!
                        val harga_final = harga_obat - hitung_diskon
                        tv_produkHargaFinal.text = rupiah(harga_final)
                    }

                    tv_produkNama.text = nama_obat
                    tv_produkJenis.text = jenis_obat
                    tv_produkHargaAsli.text = rupiah(harga_obat!!.toDouble())
                    tv_produkDiskon.text = diskon_obat.toString() + "%"
                    tv_produkDeskripsi.text = deskripsi_obat

                    Glide.with(this@DetailProdukActivity)
                        .load(gambar_obat)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .placeholder(R.color.white)
                        .into(iv_produkGambar)
                }

                override fun onFailure(call: Call<ObatResponseModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
        retrieveDetailObat(id_produk)

    }

    private fun rupiah(number: Double): String {
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number)
    }

    private fun bukaKeranjang(){
        val intent = Intent(this, KeranjangActivity::class.java)
        startActivity(intent)
    }
}