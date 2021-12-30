package com.uty.apotekku

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btnback: ImageButton = findViewById(R.id.profile_back)
        val btnchart: ImageButton = findViewById(R.id.profile_chart)

        btnchart.setOnClickListener{bukaKeranjang()}
        btnback.setOnClickListener{finish()}
    }

    private fun bukaKeranjang(){
        val intent = Intent(this, KeranjangActivity::class.java)
        startActivity(intent)
    }
}