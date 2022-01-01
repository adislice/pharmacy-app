package com.uty.apotekku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val checkoutProductDetail: RecyclerView = findViewById(R.id.rv_checkout_produk)
        val checkoutHitungDetail: RecyclerView = findViewById(R.id.checkout_hitungan_subtotal)

        val checkoutList = ArrayList<CheckoutModel>()
        checkoutList.add(CheckoutModel("Bisolvon Extra", 10000, 3, R.drawable.bisolvon_extra))
        checkoutList.add(CheckoutModel("Masker 3-ply Hygenix (isi 50)", 50000, 1, R.drawable.masker))

        val checkoutHitung = ArrayList<CheckoutHitungModel>()
        checkoutHitung.add(CheckoutHitungModel("Subtotal pesanan",60000))
        checkoutHitung.add(CheckoutHitungModel("Biaya pengiriman",1000))
        checkoutHitung.add(CheckoutHitungModel("Biaya layanan",2000))
        checkoutHitung.add(CheckoutHitungModel("Subtotal diskon pesanan",21000))

        val checkoutProductDetailViewAdapter : RecyclerView.Adapter<*> = CheckoutAdapter(checkoutList)
        val checkoutProductDetailViewManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
        checkoutProductDetail.apply {
            setHasFixedSize(true)
            adapter = checkoutProductDetailViewAdapter
            layoutManager = checkoutProductDetailViewManager
        }

        val checkoutHitungDetailViewAdapter : RecyclerView.Adapter<*> = CheckoutHitungAdapter(checkoutHitung)
        val checkoutHitungDetailViewManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
        checkoutHitungDetail.apply {
            setHasFixedSize(true)
            adapter = checkoutHitungDetailViewAdapter
            layoutManager = checkoutHitungDetailViewManager
        }

        val type = arrayOf("DANA", "OVO", "ShopeePay", "ATM Bersama", "PayPal")

        val adapter = ArrayAdapter(this,R.layout.list_metode_pembayaran,type)

        val editTextFilledExposedDropdown = findViewById<AutoCompleteTextView>(R.id.checkout_metode_pembayaran)
        editTextFilledExposedDropdown.setAdapter(adapter)
    }
}