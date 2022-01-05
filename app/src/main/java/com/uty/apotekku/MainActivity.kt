package com.uty.apotekku

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.uty.apotekku.API.APIRequestData
import com.uty.apotekku.API.RetroServer
import com.uty.apotekku.Model.AlkesDataModel
import com.uty.apotekku.Model.AlkesResponseModel
import com.uty.apotekku.Model.ObatDataModel
import com.uty.apotekku.Model.ObatResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList
import com.uty.apotekku.Adapter.ObatAdapter
import com.uty.apotekku.Adapter.AlkesAdapter

class MainActivity : AppCompatActivity() {

    private var obatList = ArrayList<ObatDataModel>()
    private lateinit var obatViewAdapter: RecyclerView.Adapter<*>
    private lateinit var obatView: RecyclerView
    private lateinit var obatViewManager: LinearLayoutManager
    private var alkesList = ArrayList<AlkesDataModel>()
    private lateinit var alkesViewAdapter: RecyclerView.Adapter<*>
    private lateinit var alkesView: RecyclerView
    private lateinit var alkesViewManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bannerView: RecyclerView = findViewById(R.id.banner_rview)
        val btnKeranjang: ImageButton = findViewById(R.id.btn_keranjang)
        val menuBar: BottomNavigationView = findViewById(R.id.bottom_nav)
        val btnlihatdaftarobat: Button = findViewById(R.id.btn_lihat_semua_obat)
        val btnlihatdaftaralkes: Button = findViewById(R.id.btn_lihat_semua_alat)

        val bannerList = ArrayList<BannerModel>()
        bannerList.add(BannerModel(
            "6 Rekomendasi Vitamin Otak Terbaik untuk Lansia",
            "https://avrecxjx.sirv.com/R0149rzMsI/aWOIUHVr97/news1.jpg",
            "https://artikel.farmaku.com/artikel/rekomendasi-vitamin-otak-terbaik-untuk-lansia/"))
        bannerList.add(BannerModel(
            "Rekomendasi Sunscreen untuk Melindungi Kulit dan Mencerahkan Wajah",
            "https://avrecxjx.sirv.com/R0149rzMsI/aWOIUHVr97/news2.jpg",
            "https://artikel.farmaku.com/artikel/rekomendasi-sunscreen-untuk-melindungi-kulit-dan-mencerahkan-wajah/"))
        bannerList.add(BannerModel(
            "6 Penyebab Sakit Kepala dan Cara Mengatasinya",
            "https://avrecxjx.sirv.com/R0149rzMsI/aWOIUHVr97/news3.jpg",
            "https://artikel.farmaku.com/artikel/penyebab-sakit-kepala/"))

        val bannerViewAdapter : RecyclerView.Adapter<*> = BannerAdapter(bannerList)
        val bannerViewManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        bannerView.apply {
            setHasFixedSize(true)
            adapter = bannerViewAdapter
            layoutManager = bannerViewManager
        }

        obatView = findViewById(R.id.obat_list_rview)
        obatViewManager = LinearLayoutManager(this)

        alkesView = findViewById(R.id.alat_list_rview)
        alkesViewManager = LinearLayoutManager(this)

        btnKeranjang.setOnClickListener{bukaKeranjang()}
        btnlihatdaftarobat.setOnClickListener{bukaDaftarObat()}
        btnlihatdaftaralkes.setOnClickListener{bukaDaftarAlkes()}

        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    // put your code here
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_obat -> {
                    bukaDaftarObat()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_alat -> {
                    bukaDaftarAlkes()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_profile -> {
                    bukaProfil()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
        menuBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        retriveDataObat()
        retriveDataAlkes()
    }

    private fun bukaProfil(){
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    private fun bukaKeranjang(){
        val intent = Intent(this, KeranjangActivity::class.java)
        startActivity(intent)
    }

    private fun bukaDaftarObat(){
        val intent = Intent(this, DaftarObatActivity::class.java)
        startActivity(intent)
    }

    private fun bukaDaftarAlkes(){
        val intent = Intent(this, DaftarAlkesActivity::class.java)
        startActivity(intent)
    }

    private fun retriveDataObat(){
        val ardData: APIRequestData = RetroServer.konekRetrofit()!!.create(APIRequestData::class.java)
        val tampilData: Call<ObatResponseModel> = ardData.ardRetriveData("get_daftar_obat")
        tampilData.enqueue(object: Callback<ObatResponseModel> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ObatResponseModel>,
                response: Response<ObatResponseModel>
            ) {
                obatList = response.body()!!.result
                obatViewAdapter = ObatAdapter(obatList, 5)
                obatView.apply {
                    setHasFixedSize(true)
                    adapter = obatViewAdapter
                    layoutManager = obatViewManager
                }
                obatViewAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ObatResponseModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "gagal menghubungkan ke server", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun retriveDataAlkes(){
        val ardData: APIRequestData = RetroServer.konekRetrofit()!!.create(APIRequestData::class.java)
        val tampilData: Call<AlkesResponseModel> = ardData.alkRetriveData("get_daftar_alkes")
        tampilData.enqueue(object: Callback<AlkesResponseModel> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<AlkesResponseModel>,
                response: Response<AlkesResponseModel>
            ) {
                alkesList = response.body()!!.result
                alkesViewAdapter = AlkesAdapter(alkesList, 5)
                alkesView.apply {
                    setHasFixedSize(true)
                    adapter = alkesViewAdapter
                    layoutManager = alkesViewManager
                }
                alkesViewAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<AlkesResponseModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "gagal menghubungkan ke server", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}
