package com.uty.apotekku

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton

class DetailObatActivity : AppCompatActivity() {

    private val etqty: EditText = findViewById(R.id.produk_qty)
    private var qty: Int = Integer.parseInt(etqty.getText().toString())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_obat)

        val btnback: ImageButton = findViewById(R.id.produk_back)
        val btnchart: ImageButton = findViewById(R.id.produk_keranjang)
        val btntambah: ImageButton = findViewById(R.id.produk_qty_tambah)
        val btnkurang: ImageButton = findViewById(R.id.produk_qty_kurang)

        btnback.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnchart.setOnClickListener {
            val intent = Intent(this, KeranjangActivity::class.java)
            startActivity(intent)
            finish()
        }

        btntambah.setOnClickListener {
            qty += 1
        }

        btnkurang.setOnClickListener {
            qty -= 1
        }
    }
}