package com.uty.apotekku

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bannerView: RecyclerView = findViewById(R.id.banner_rview)
        val obatView: RecyclerView = findViewById(R.id.obat_list_rview)
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

        val obatList = ArrayList<ObatModel>()
        obatList.add(ObatModel("Bisolvon Extra", "Sirup", "20000", R.drawable.bisolvon_extra))
        obatList.add(ObatModel("Bisolvon Extra", "Sirup", "20000", R.drawable.bisolvon_extra))
        obatList.add(ObatModel("Bisolvon Extra", "Sirup", "20000", R.drawable.bisolvon_extra))
        obatList.add(ObatModel("Bisolvon Extra", "Sirup", "20000", R.drawable.bisolvon_extra))
        obatList.add(ObatModel("Bisolvon Extra", "Sirup", "20000", R.drawable.bisolvon_extra))
        obatList.add(ObatModel("Bisolvon Extra", "Sirup", "20000", R.drawable.bisolvon_extra))

        val obatViewAdapter: RecyclerView.Adapter<*> = ObatAdapter(obatList,5)
        val obatViewManager = LinearLayoutManager(this)
        obatView.apply {
            setHasFixedSize(true)
            adapter = obatViewAdapter
            layoutManager = obatViewManager
        }

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
}
