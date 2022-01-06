package com.uty.apotekku

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.uty.apotekku.API.APIRequestData
import com.uty.apotekku.API.RetroServer
import com.uty.apotekku.Model.KeranjangResponseModel
import com.uty.apotekku.Adapter.KeranjangAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KeranjangActivity : AppCompatActivity() {
    private lateinit var keranjangRV: RecyclerView
    private lateinit var keranjangViewAdapter: RecyclerView.Adapter<*>
    private lateinit var keranjangViewManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keranjang)

        keranjangRV = findViewById(R.id.rv_produk)
        keranjangViewManager = LinearLayoutManager(this)
        val btnbayar: Button = findViewById(R.id.btn_bayar)
        val btnback: ImageButton = findViewById(R.id.keranjang_back)

        val id_user = intent.getIntExtra("id_user", 0)

//        val keranjangList = ArrayList<KeranjangModel>()
//        keranjangList.add(KeranjangModel("Bisolvon Extra",10000,1))
//        keranjangList.add(KeranjangModel("Bisolvon Extra",10000,1))
//        keranjangList.add(KeranjangModel("Bisolvon Extra",10000,1))
//        keranjangList.add(KeranjangModel("Bisolvon Extra",10000,1))
//        keranjangList.add(KeranjangModel("Bisolvon Extra",10000,1))

//        val keranjangViewAdapter: RecyclerView.Adapter<*> = KeranjangAdapter(keranjangList)
        val keranjangViewManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

//        keranjangView.apply {
//            setHasFixedSize(true)
//            adapter = keranjangViewAdapter
//            layoutManager = keranjangViewManager

//        }

        btnbayar.setOnClickListener{prosesCheckout()}
        btnback.setOnClickListener {finish()}
        lihat_keranjang(id_user)
    }

    private fun prosesCheckout(){
        val intent = Intent(this, CheckoutActivity::class.java)
        startActivity(intent)
    }

    private fun lihat_keranjang(id_user: Int){
        val ardData: APIRequestData = RetroServer.konekRetrofit()!!.create(APIRequestData::class.java)
        val tampilData: Call<KeranjangResponseModel> = ardData.lihat_keranjang("lihat_keranjang_semua", id_user)
        tampilData.enqueue(object: Callback<KeranjangResponseModel> {
            override fun onResponse(
                call: Call<KeranjangResponseModel>,
                response: Response<KeranjangResponseModel>
            ) {
                var krjStatus = response.body()!!.status
                Toast.makeText(this@KeranjangActivity, id_user.toString(), Toast.LENGTH_SHORT).show()
                if (krjStatus == true) {

                    var krjResult = response.body()!!.result
                    keranjangViewAdapter = KeranjangAdapter(krjResult)
                    keranjangRV.apply{
                        setHasFixedSize(true)
                        adapter = keranjangViewAdapter
                        layoutManager = keranjangViewManager
                    }
                    keranjangViewAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<KeranjangResponseModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}