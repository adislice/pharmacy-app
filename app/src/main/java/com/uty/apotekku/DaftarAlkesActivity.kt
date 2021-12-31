package com.uty.apotekku

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

class DaftarAlkesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_obat)

        val detailProdukObat: RecyclerView = findViewById(R.id.rv_daftar_obat)
        val daftarProdukList = ArrayList<DaftarProdukModel>()
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
        val detailProdukViewManager = GridLayoutManager(this, 2)
        detailProdukObat.apply {
            setHasFixedSize(true)
            adapter = detailProdukViewAdapter
            layoutManager = detailProdukViewManager
        }
    }
}