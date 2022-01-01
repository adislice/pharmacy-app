package com.uty.apotekku

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.ImageButton

class KeranjangActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keranjang)

        val keranjangView: RecyclerView = findViewById(R.id.rv_produk)
        val btnbayar: Button = findViewById(R.id.btn_bayar)
        val btnback: ImageButton = findViewById(R.id.keranjang_back)

        val keranjangList = ArrayList<KeranjangModel>()
        keranjangList.add(KeranjangModel("Bisolvon Extra",10000,1))
        keranjangList.add(KeranjangModel("Bisolvon Extra",10000,1))
        keranjangList.add(KeranjangModel("Bisolvon Extra",10000,1))
        keranjangList.add(KeranjangModel("Bisolvon Extra",10000,1))
        keranjangList.add(KeranjangModel("Bisolvon Extra",10000,1))

        val keranjangViewAdapter: RecyclerView.Adapter<*> = KeranjangAdapter(keranjangList)
        val keranjangViewManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        keranjangView.apply {
            setHasFixedSize(true)
            adapter = keranjangViewAdapter
            layoutManager = keranjangViewManager
        }

        btnbayar.setOnClickListener{prosesCheckout()}
        btnback.setOnClickListener {finish()}
    }

    private fun prosesCheckout(){
        val intent = Intent(this, CheckoutActivity::class.java)
        startActivity(intent)
    }
}