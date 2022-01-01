package com.uty.apotekku

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageButton

class DaftarObatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_obat)

        val detailProdukObat: RecyclerView = findViewById(R.id.rv_daftar_obat)
        val daftarProdukList = ArrayList<DaftarProdukModel>()
        val btnback: ImageButton = findViewById(R.id.produk_back)
        val btnkeranjang: ImageButton = findViewById(R.id.produk_keranjang)

        btnback.setOnClickListener {finish()}
        btnkeranjang.setOnClickListener {bukaKeranjang()}

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

    private fun bukaKeranjang(){
        val intent = Intent(this@DaftarObatActivity, KeranjangActivity::class.java)
        startActivity(intent)
    }
}