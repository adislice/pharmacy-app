package com.uty.apotekku

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

class DaftarObatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_obat)

        val detailProdukObat: RecyclerView = findViewById(R.id.rv_daftar_obat)
        val daftarProdukList = ArrayList<DaftarProdukModel>()
        daftarProdukList.add(DaftarProdukModel("Bisolvon Extra", 10, 10000, R.drawable.bisolvon_extra))
        daftarProdukList.add(DaftarProdukModel("Bisolvon Extra", 10, 10000, R.drawable.bisolvon_extra))
        daftarProdukList.add(DaftarProdukModel("Bisolvon Extra", 10, 10000, R.drawable.bisolvon_extra))
        daftarProdukList.add(DaftarProdukModel("Bisolvon Extra", 10, 10000, R.drawable.bisolvon_extra))
        daftarProdukList.add(DaftarProdukModel("Bisolvon Extra", 10, 10000, R.drawable.bisolvon_extra))
        daftarProdukList.add(DaftarProdukModel("Bisolvon Extra", 10, 10000, R.drawable.bisolvon_extra))
        daftarProdukList.add(DaftarProdukModel("Bisolvon Extra", 10, 10000, R.drawable.bisolvon_extra))
        daftarProdukList.add(DaftarProdukModel("Bisolvon Extra", 10, 10000, R.drawable.bisolvon_extra))
        daftarProdukList.add(DaftarProdukModel("Bisolvon Extra", 10, 10000, R.drawable.bisolvon_extra))
        daftarProdukList.add(DaftarProdukModel("Bisolvon Extra", 10, 10000, R.drawable.bisolvon_extra))

        val detailProdukViewAdapter : RecyclerView.Adapter<*> = DaftarProdukAdapter(daftarProdukList)
        val detailProdukViewManager = GridLayoutManager(this, 2)
        detailProdukObat.apply {
            setHasFixedSize(true)
            adapter = detailProdukViewAdapter
            layoutManager = detailProdukViewManager
        }
    }
}