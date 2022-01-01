package com.uty.apotekku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageButton

class DaftarAlkesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_obat)

        val detailProdukObat: RecyclerView = findViewById(R.id.rv_daftar_obat)
        val daftarProdukList = ArrayList<DaftarProdukModel>()
        val btnback: ImageButton = findViewById(R.id.produk_back)
        val btnkeranjang: ImageButton = findViewById(R.id.produk_keranjang)

        btnback.setOnClickListener {finish()}
        btnkeranjang.setOnClickListener {bukaKeranjang()}

        daftarProdukList.add(DaftarProdukModel("Masker 3-ply Hygenix (isi 50)", 20, 20000, R.drawable.masker))
        daftarProdukList.add(DaftarProdukModel("Masker 3-ply Hygenix (isi 50)", 20, 20000, R.drawable.masker))
        daftarProdukList.add(DaftarProdukModel("Masker 3-ply Hygenix (isi 50)", 20, 20000, R.drawable.masker))
        daftarProdukList.add(DaftarProdukModel("Masker 3-ply Hygenix (isi 50)", 20, 20000, R.drawable.masker))
        daftarProdukList.add(DaftarProdukModel("Masker 3-ply Hygenix (isi 50)", 20, 20000, R.drawable.masker))
        daftarProdukList.add(DaftarProdukModel("Masker 3-ply Hygenix (isi 50)", 20, 20000, R.drawable.masker))
        daftarProdukList.add(DaftarProdukModel("Masker 3-ply Hygenix (isi 50)", 20, 20000, R.drawable.masker))
        daftarProdukList.add(DaftarProdukModel("Masker 3-ply Hygenix (isi 50)", 20, 20000, R.drawable.masker))
        daftarProdukList.add(DaftarProdukModel("Masker 3-ply Hygenix (isi 50)", 20, 20000, R.drawable.masker))
        daftarProdukList.add(DaftarProdukModel("Masker 3-ply Hygenix (isi 50)", 20, 20000, R.drawable.masker))

        val detailProdukViewAdapter : RecyclerView.Adapter<*> = DaftarProdukAdapter(daftarProdukList)
        val detailProdukViewManager =
            GridLayoutManager(this, 2)
        detailProdukObat.apply {
            setHasFixedSize(true)
            adapter = detailProdukViewAdapter
            layoutManager = detailProdukViewManager
        }
    }

    private fun bukaKeranjang(){
        val intent = Intent(this@DaftarAlkesActivity, KeranjangActivity::class.java)
        startActivity(intent)
    }
}