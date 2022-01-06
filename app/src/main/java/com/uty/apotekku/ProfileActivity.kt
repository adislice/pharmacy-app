package com.uty.apotekku

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.uty.apotekku.API.APIRequestData
import com.uty.apotekku.API.RetroServer
import com.uty.apotekku.Model.ObatResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {

    private lateinit var tvprofileNama: TextView
    private lateinit var tvprofileUsername: TextView
    private lateinit var tvprofileAlamat: TextView
    private lateinit var tvprofileNoTelp: TextView
    private lateinit var tvprofileEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val id_user = intent.getIntExtra("id_user", 0)
        val btnback: ImageButton = findViewById(R.id.profile_back)
        val btnchart: ImageButton = findViewById(R.id.profile_chart)

        btnchart.setOnClickListener{bukaKeranjang(id_user)}
        btnback.setOnClickListener{finish()}

        tvprofileNama = findViewById(R.id.profile_nama_user)
        tvprofileUsername = findViewById(R.id.profile_username_user)
        tvprofileAlamat = findViewById(R.id.profile_alamat_user)
        tvprofileNoTelp = findViewById(R.id.profile_no_telp_user)
        tvprofileEmail = findViewById(R.id.profile_email_user)
    }

    private fun bukaKeranjang(id_user: Int){
        val intent = Intent(this, KeranjangActivity::class.java)
        intent.putExtra("id_user", id_user)
        startActivity(intent)
    }


}