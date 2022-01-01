package com.uty.apotekku

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageButton
import android.widget.Toast
import com.uty.apotekku.API.APIRequestData
import com.uty.apotekku.API.RetroServer
import com.uty.apotekku.Model.ObatDataModel
import com.uty.apotekku.Model.ObatResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.uty.apotekku.Adapter.ObatAdapter as NewObatAdapter

class MainActivity : AppCompatActivity() {
    private var obatList = ArrayList<ObatDataModel>()
    private lateinit var obatViewAdapter: RecyclerView.Adapter<*>
    private lateinit var obatView: RecyclerView
    private lateinit var obatViewManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bannerView: RecyclerView = findViewById(R.id.banner_rview)

        val alatView: RecyclerView = findViewById(R.id.alat_list_rview)
        val btnKeranjang: ImageButton = findViewById(R.id.btn_keranjang)
        val menuBar: BottomNavigationView = findViewById(R.id.bottom_nav)

        val bannerList = ArrayList<BannerModel>()
        bannerList.add(BannerModel("Konsumsi Buah-buahan Dapat Membantu Meringankan Stress", R.drawable.buah))
        bannerList.add(BannerModel("Pelajari Cara Hidup Sehat Seperti Larry", R.drawable.buah))
        bannerList.add(BannerModel("Jangan Terlalu Banyak Begadang, Ketahui Resikonya", R.drawable.buah))

        val bannerViewAdapter : RecyclerView.Adapter<*> = BannerAdapter(bannerList)
        val bannerViewManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        bannerView.apply {
            setHasFixedSize(true)
            adapter = bannerViewAdapter
            layoutManager = bannerViewManager
        }
        obatView = findViewById(R.id.obat_list_rview)
        obatViewManager = LinearLayoutManager(this)
        val obatList = ArrayList<ObatDataModel>()
//        obatList.add(ObatModel("Bisolvon Extra", "Sirup", "20000", R.drawable.bisolvon_extra))
//        obatList.add(ObatModel("Bisolvon Extra", "Sirup", "20000", R.drawable.bisolvon_extra))
//        obatList.add(ObatModel("Bisolvon Extra", "Sirup", "20000", R.drawable.bisolvon_extra))
//        obatList.add(ObatModel("Bisolvon Extra", "Sirup", "20000", R.drawable.bisolvon_extra))
//        obatList.add(ObatModel("Bisolvon Extra", "Sirup", "20000", R.drawable.bisolvon_extra))
//        obatList.add(ObatModel("Bisolvon Extra", "Sirup", "20000", R.drawable.bisolvon_extra))

//         = NewObatAdapter(obatList,5)

//        obatView.apply {
//            setHasFixedSize(true)
//            adapter = obatViewAdapter
//            layoutManager = obatViewManager
//        }

        val alatList = ArrayList<ObatModel>()
        alatList.add(ObatModel("Masker 3-ply Hygenix (isi 50)", "Perlengkapan", "50000", R.drawable.masker))
        alatList.add(ObatModel("Masker 3-ply Hygenix (isi 50)", "Perlengkapan", "50000", R.drawable.masker))
        alatList.add(ObatModel("Masker 3-ply Hygenix (isi 50)", "Perlengkapan", "50000", R.drawable.masker))
        alatList.add(ObatModel("Masker 3-ply Hygenix (isi 50)", "Perlengkapan", "50000", R.drawable.masker))
        alatList.add(ObatModel("Masker 3-ply Hygenix (isi 50)", "Perlengkapan", "50000", R.drawable.masker))
        alatList.add(ObatModel("Masker 3-ply Hygenix (isi 50)", "Perlengkapan", "50000", R.drawable.masker))

        val alatViewAdapter: RecyclerView.Adapter<*> = ObatAdapter(alatList, 5)
        val alatViewManager = LinearLayoutManager(this)
        alatView.apply {
            setHasFixedSize(true)
            adapter = alatViewAdapter
            layoutManager = alatViewManager
        }

        btnKeranjang.setOnClickListener{
            bukaKeranjang()
        }

        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    // put your code here
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_obat -> {
                    bukaDetailProduk()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_alat -> {
                    // put your code here
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

        retrieveDataObat()
    }

    private fun bukaProfil(){
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    private fun bukaKeranjang(){
        val intent = Intent(this, KeranjangActivity::class.java)
        startActivity(intent)
    }

    private fun bukaDetailProduk(){
        val intent = Intent(this, DetailProdukActivity::class.java)
        startActivity(intent)
    }

    private fun retrieveDataObat(){
        var ardData: APIRequestData = RetroServer.konekRetrofit()!!.create(APIRequestData::class.java)
        var tampilData: Call<ObatResponseModel> = ardData.ardRetriveData("get_daftar_obat")
        tampilData.enqueue(object: Callback<ObatResponseModel> {
            override fun onResponse(
                call: Call<ObatResponseModel>,
                response: Response<ObatResponseModel>
            ) {
                val status = response.body()?.status.toString()
//                Toast.makeText(this@MainActivity, "Status : "+status,Toast.LENGTH_SHORT)
//                    .show()
                obatList = response.body()!!.result
                obatViewAdapter = NewObatAdapter(obatList, 5)
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
}
