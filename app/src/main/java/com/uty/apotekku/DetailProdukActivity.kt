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
import com.uty.apotekku.Model.CekKuantitasResponseModel
import com.uty.apotekku.Model.ObatResponseModel
import com.uty.apotekku.Model.TambahObatKeKeranjangResponseModel
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
    private lateinit var btntambahkeranjang: Button
    private var qty: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_produk)

        val id_user = intent.getIntExtra("id_user", 0)
        val kategori = intent.getStringExtra("kategori")
        var idobat: Int = 0
        var idalkes: Int = 0
        if (kategori == "obat") {
            idobat = intent.getIntExtra("id_obat", 1)
            retrieveDetailObat(idobat)
        } else if (kategori == "alkes"){
            idalkes = intent.getIntExtra("id_alkes", 1)
            retrieveDetailAlkes(idalkes)
        }

        val btnback: ImageButton = findViewById(R.id.produk_back)
        val btnchart: ImageButton = findViewById(R.id.produk_keranjang)
        val btntambah: ImageButton = findViewById(R.id.produk_qty_tambah)
        val btnkurang: ImageButton = findViewById(R.id.produk_qty_kurang)

        btntambahkeranjang = findViewById(R.id.produk_tambah_keranjang)
        ivprodukGambar = findViewById(R.id.produk_gambar)
        tvprodukNama = findViewById(R.id.produk_nama)
        tvprodukHargaAsli = findViewById(R.id.produk_harga_asli)
        tvprodukHargaFinal = findViewById(R.id.produk_harga_final)
        tvprodukDiskon = findViewById(R.id.produk_diskon)
        tvprodukJenis = findViewById(R.id.produk_jenis)
        tvprodukDeskripsi = findViewById(R.id.produk_deskripsi)

        btnback.setOnClickListener {finish()}
        btnchart.setOnClickListener {bukaKeranjang(id_user)}
        btntambahkeranjang.setOnClickListener{
            Toast.makeText(applicationContext, "kuantitas : " + etqty.text.toString().toInt(), Toast.LENGTH_SHORT).show()
            if (kategori == "obat") {
                tambahObatKeKeranjang(id_user, idobat, etqty.text.toString().toInt())
            }
        }

        etqty = findViewById(R.id.produk_qty)
        qty = etqty.text.toString().toInt()

        btntambah.setOnClickListener {
            qty ++
            etqty.setText(qty.toString())
        }
        btnkurang.setOnClickListener {
            if (qty > 1){
                qty --
                etqty.setText(qty.toString())
            }
        }

        if (kategori == "obat") {
            cekKuantitasObat(id_user, idobat)
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
                tvprodukJenis.visibility = View.GONE

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

    private fun cekKuantitasObat(id_user: Int, id_obat: Int){
        val ardData: APIRequestData = RetroServer.konekRetrofit()!!.create(APIRequestData::class.java)
        val qtyResult : Call<CekKuantitasResponseModel> = ardData.cekQtyObat("cek_qty_obat", id_user, id_obat)
        qtyResult.enqueue(object: Callback<CekKuantitasResponseModel> {
            override fun onResponse(
                call: Call<CekKuantitasResponseModel>,
                response: Response<CekKuantitasResponseModel>
            ) {
                val status = response.body()!!.status
                val qty = response.body()!!.qty
                if (qty > 0) {
                    etqty.setText(qty.toString())
                }
            }

            override fun onFailure(call: Call<CekKuantitasResponseModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun rupiah(number: Double): String {
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        numberFormat.maximumFractionDigits = 0
        return numberFormat.format(number)
    }

    private fun bukaKeranjang(id_user: Int){
        val intent = Intent(this, KeranjangActivity::class.java)
        intent.putExtra("id_user", id_user)
        startActivity(intent)
    }

    private fun tambahObatKeKeranjang(id_user: Int, id_obat: Int, qty: Int){
        val ardData: APIRequestData = RetroServer.konekRetrofit()!!.create(APIRequestData::class.java)
        val tambahObatResult: Call<TambahObatKeKeranjangResponseModel> = ardData.tambahObatKeKrj("tambah_obat_ke_keranjang", id_user, id_obat, qty)
        tambahObatResult.enqueue(object: Callback<TambahObatKeKeranjangResponseModel> {
            override fun onResponse(
                call: Call<TambahObatKeKeranjangResponseModel>,
                response: Response<TambahObatKeKeranjangResponseModel>
            ) {
                val status = response.body()!!.status
                val msg = response.body()!!.msg
                if (status) {
                    if (msg == "insert-sukses") {
                        Toast.makeText(this@DetailProdukActivity, "Berhasil Menambahkan ke Keranjang", Toast.LENGTH_SHORT)
                            .show()
                    } else if (msg == "update-sukses") {
                        Toast.makeText(this@DetailProdukActivity, "Berhasil Mengubah Kuantitas", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            override fun onFailure(call: Call<TambahObatKeKeranjangResponseModel>, t: Throwable) {
                Toast.makeText(this@DetailProdukActivity, "Gagal Menambahkan ke Keranjang", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }
}