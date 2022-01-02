package com.uty.apotekku

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.uty.apotekku.API.APIRequestData
import com.uty.apotekku.API.RetroServer
import com.uty.apotekku.Model.AlkesResponseModel
import com.uty.apotekku.Model.ObatResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*

class DetailProdukActivity : AppCompatActivity() {

    private lateinit var etqty: EditText
    private lateinit var ivprodukGambar: ImageView
    private lateinit var tvprodukNama: TextView
    private lateinit var tvprodukHargaAsli: TextView
    private lateinit var tvprodukHargaFinal: TextView
    private lateinit var tvprodukJenis: TextView
    private lateinit var tvprodukDiskon: TextView
    private lateinit var tvprodukDeskripsi: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_produk)

        val kategori = intent.getStringExtra("kategori")
        var idobat = 0
        var idalkes = 0
        if (kategori == "obat") {
            idobat = intent.getIntExtra("id_obat", 1)
        } else if (kategori == "alkes"){
            idalkes = intent.getIntExtra("id_alkes", 1)
        }

        val btnback: ImageButton = findViewById(R.id.produk_back)
        val btnchart: ImageButton = findViewById(R.id.produk_keranjang)
        val btntambah: ImageButton = findViewById(R.id.produk_qty_tambah)
        val btnkurang: ImageButton = findViewById(R.id.produk_qty_kurang)

        ivprodukGambar = findViewById(R.id.produk_gambar)
        tvprodukNama = findViewById(R.id.produk_nama)
        tvprodukHargaAsli = findViewById(R.id.produk_harga_asli)
        tvprodukHargaFinal = findViewById(R.id.produk_harga_final)
        tvprodukDiskon = findViewById(R.id.produk_diskon)
        tvprodukJenis = findViewById(R.id.produk_jenis)
        tvprodukDeskripsi = findViewById(R.id.produk_deskripsi)

        etqty = findViewById(R.id.produk_qty)
        var qty: Int = etqty.text.toString().toInt()

        btnback.setOnClickListener {finish()}
        btnchart.setOnClickListener {bukaKeranjang()}

        btntambah.setOnClickListener {
            qty ++
            etqty.setText(qty.toString())
        }
        btnkurang.setOnClickListener {
            if (qty > 0){
                qty --
                etqty.setText(qty.toString())
            }
        }

        etqty.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                qty = etqty.text.toString().toInt()
            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {
                // TODO
            }
            override fun afterTextChanged(s: Editable) {
                // TODO
            }
        })

        if (kategori == "obat") {
            retrieveDetailObat(idobat)
        } else if (kategori == "alkes"){
            retrieveDetailAlkes(idalkes)
        }
    }

    private fun retrieveDetailObat(id_produk: Int){
        val ardData: APIRequestData = RetroServer.konekRetrofit()!!.create(APIRequestData::class.java)
        val tampilObat: Call<ObatResponseModel> = ardData.getDetailObat("get_obat", id_produk)
        tampilObat.enqueue(object: Callback<ObatResponseModel>{
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<ObatResponseModel>,
                response: Response<ObatResponseModel>
            ) {
                val obat = response.body()!!.result
                val namaobat = obat[0].nama_obat
                val hargaobat = obat[0].harga
                val jenisobat = obat[0].jenis_obat
                val deskripsiobat = obat[0].deskripsi
                val gambarobat = obat[0].gambar
                val diskonobat = obat[0].diskon

                if (diskonobat == 0){
                    tvprodukDiskon.visibility = View.GONE
                    tvprodukHargaFinal.text = hargaobat?.let { rupiah(it.toDouble()) }
                    tvprodukHargaAsli.visibility = View.GONE
                } else {
                    val hitungdiskon: Double = (diskonobat!!.toDouble() / 100) * hargaobat!!
                    val hargafinal = hargaobat - hitungdiskon
                    tvprodukHargaFinal.text = rupiah(hargafinal)
                }

                tvprodukNama.text = namaobat
                tvprodukJenis.text = jenisobat
                tvprodukHargaAsli.text = rupiah(hargaobat!!.toDouble())
                tvprodukDiskon.text = "$diskonobat%"
                tvprodukDeskripsi.text = deskripsiobat

                Glide.with(this@DetailProdukActivity)
                    .load(gambarobat)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.color.white)
                    .into(ivprodukGambar)
            }

            override fun onFailure(call: Call<ObatResponseModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun retrieveDetailAlkes(id_produk: Int){
        val ardData: APIRequestData = RetroServer.konekRetrofit()!!.create(APIRequestData::class.java)
        val tampilAlkes: Call<AlkesResponseModel> = ardData.getDetailAlkes("get_alkes", id_produk)
        tampilAlkes.enqueue(object: Callback<AlkesResponseModel>{
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<AlkesResponseModel>,
                response: Response<AlkesResponseModel>
            ) {
                val alkes = response.body()!!.result
                val namaalkes = alkes[0].nama_alkes
                val hargaalkes = alkes[0].harga
                val deskripsialkes = alkes[0].deskripsi
                val gambaralkes = alkes[0].gambar
                val diskonalkes = alkes[0].diskon

                if (diskonalkes == 0){
                    tvprodukDiskon.visibility = View.GONE
                    tvprodukHargaFinal.text = hargaalkes?.let { rupiah(it.toDouble()) }
                    tvprodukHargaAsli.visibility = View.GONE
                } else {
                    val hitungdiskon: Double = (diskonalkes!!.toDouble() / 100) * hargaalkes!!
                    val hargafinal = hargaalkes - hitungdiskon
                    tvprodukHargaFinal.text = rupiah(hargafinal)
                }

                tvprodukNama.text = namaalkes
                tvprodukHargaAsli.text = rupiah(hargaalkes!!.toDouble())
                tvprodukDiskon.text = "$diskonalkes%"
                tvprodukDeskripsi.text = deskripsialkes

                Glide.with(this@DetailProdukActivity)
                    .load(gambaralkes)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.color.white)
                    .into(ivprodukGambar)
            }

            override fun onFailure(call: Call<AlkesResponseModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
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