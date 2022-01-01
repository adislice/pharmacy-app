package com.uty.apotekku

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageButton

class DetailProdukActivity : AppCompatActivity() {

    private lateinit var etqty: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_produk)

        val btnback: ImageButton = findViewById(R.id.produk_back)
        val btnchart: ImageButton = findViewById(R.id.produk_keranjang)
        val btntambah: ImageButton = findViewById(R.id.produk_qty_tambah)
        val btnkurang: ImageButton = findViewById(R.id.produk_qty_kurang)

        etqty = findViewById(R.id.produk_qty)
        var qty: Int = etqty.text.toString().toInt()

        btnback.setOnClickListener {finish()}
        btnchart.setOnClickListener {bukaKeranjang()}

        btntambah.setOnClickListener {
            qty ++
            etqty.setText(qty.toString())
        }
        btnkurang.setOnClickListener {
            if (qty > 0){
                qty --
                etqty.setText(qty.toString())
            }
        }

        etqty.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                qty = etqty.text.toString().toInt()
            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {
                // TODO
            }
            override fun afterTextChanged(s: Editable) {
                // TODO
            }
        })
    }

    private fun bukaKeranjang(){
        val intent = Intent(this, KeranjangActivity::class.java)
        startActivity(intent)
    }
}