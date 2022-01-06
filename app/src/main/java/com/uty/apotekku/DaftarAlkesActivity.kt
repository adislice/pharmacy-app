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
import com.uty.apotekku.Adapter.DaftarAlkesAdapter
import com.uty.apotekku.Model.DaftarAlkesDataModel
import com.uty.apotekku.Model.DaftarAlkesResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class DaftarAlkesActivity : AppCompatActivity() {

    private var alkesList = ArrayList<DaftarAlkesDataModel>()
    private lateinit var alkesViewAdapter: RecyclerView.Adapter<*>
    private lateinit var alkesView: RecyclerView
    private lateinit var alkesViewManager: GridLayoutManager
    private var id_user:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_alkes)

        id_user = intent.getIntExtra("id_user", 0)
        val daftarProdukList = ArrayList<DaftarProdukModel>()
        val btnback: ImageButton = findViewById(R.id.produk_back)
        val btnkeranjang: ImageButton = findViewById(R.id.produk_keranjang)

        btnback.setOnClickListener {finish()}
        btnkeranjang.setOnClickListener {bukaKeranjang(id_user)}

        alkesView = findViewById(R.id.rv_daftar_alkes)
        alkesViewAdapter  = DaftarProdukAdapter(daftarProdukList)
        alkesViewManager = GridLayoutManager(this, 2)

        retriveDataAlkes()
    }

    private fun bukaKeranjang(id_user: Int){
        val intent = Intent(this, KeranjangActivity::class.java)
        intent.putExtra("id_user", id_user)
        startActivity(intent)
    }

    private fun retriveDataAlkes(){
        val ardData: APIRequestData = RetroServer.konekRetrofit()!!.create(APIRequestData::class.java)
        val tampilData: Call<DaftarAlkesResponseModel> = ardData.dalkesRetriveData("get_daftar_alkes")
        id_user = intent.getIntExtra("id_user", 0)
        tampilData.enqueue(object: Callback<DaftarAlkesResponseModel> {
            override fun onResponse(
                call: Call<DaftarAlkesResponseModel>,
                response: Response<DaftarAlkesResponseModel>
            ) {
                alkesList = response.body()!!.result
                alkesViewAdapter = DaftarAlkesAdapter(alkesList, 100, id_user)
                alkesView.apply {
                    setHasFixedSize(true)
                    adapter = alkesViewAdapter
                    layoutManager = alkesViewManager
                }
                alkesViewAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<DaftarAlkesResponseModel>, t: Throwable) {
                Toast.makeText(this@DaftarAlkesActivity, "gagal menghubungkan ke server", Toast.LENGTH_SHORT)
                    .show()

            }

        })
    }
}