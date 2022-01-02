package com.uty.apotekku

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageButton
import android.widget.Toast
import com.uty.apotekku.API.APIRequestData
import com.uty.apotekku.API.RetroServer
import com.uty.apotekku.Adapter.DaftarObatAdapter
import com.uty.apotekku.Adapter.ObatAdapter
import com.uty.apotekku.Model.DaftarObatDataModel
import com.uty.apotekku.Model.DaftarObatResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class DaftarObatActivity : AppCompatActivity() {

    private var obatList = ArrayList<DaftarObatDataModel>()
    private lateinit var obatViewAdapter: RecyclerView.Adapter<*>
    private lateinit var obatView: RecyclerView
    private lateinit var obatViewManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_obat)

        val daftarProdukList = ArrayList<DaftarProdukModel>()
        val btnback: ImageButton = findViewById(R.id.produk_back)
        val btnkeranjang: ImageButton = findViewById(R.id.produk_keranjang)

        btnback.setOnClickListener {finish()}
        btnkeranjang.setOnClickListener {bukaKeranjang()}

        obatView = findViewById(R.id.rv_daftar_obat)
        obatViewAdapter  = DaftarProdukAdapter(daftarProdukList)
        obatViewManager = GridLayoutManager(this, 2)

        retriveDataObat()

    }

    private fun bukaKeranjang(){
        val intent = Intent(this@DaftarObatActivity, KeranjangActivity::class.java)
        startActivity(intent)
    }

    private fun retriveDataObat(){
        val ardData: APIRequestData = RetroServer.konekRetrofit()!!.create(APIRequestData::class.java)
        val tampilData: Call<DaftarObatResponseModel> = ardData.dobatRetriveData("get_daftar_obat")
        tampilData.enqueue(object: Callback<DaftarObatResponseModel> {
            override fun onResponse(
                call: Call<DaftarObatResponseModel>,
                response: Response<DaftarObatResponseModel>
            ) {
                obatList = response.body()!!.result
                obatViewAdapter = DaftarObatAdapter(obatList, 100)
                obatView.apply {
                    setHasFixedSize(true)
                    adapter = obatViewAdapter
                    layoutManager = obatViewManager
                }
                obatViewAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<DaftarObatResponseModel>, t: Throwable) {
                Toast.makeText(this@DaftarObatActivity, "gagal menghubungkan ke server", Toast.LENGTH_SHORT)
                    .show()

            }

        })
    }

    private fun rupiah(number: Double): String {
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number)
    }

}