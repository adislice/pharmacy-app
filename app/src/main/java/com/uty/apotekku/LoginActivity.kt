package com.uty.apotekku

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.uty.apotekku.API.APIRequestData
import com.uty.apotekku.API.RetroServer
import com.uty.apotekku.Model.LoginResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    var etEmailInputted: Boolean = false
    var etPassInputted: Boolean = false

    fun checkValidation(){
        val btnLogin = findViewById<Button>(R.id.login_btn)
        if(etEmailInputted && etPassInputted){
            btnLogin.apply{setBackgroundResource(R.drawable.bg_btn)}
            btnLogin.isEnabled = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.login_btn)
        val etEmail = findViewById<TextInputEditText>(R.id.login_et_email)
        val etPass = findViewById<TextInputEditText>(R.id.login_et_pass)
        val txtRegist = findViewById<TextView>(R.id.login_regist_go)
        val txtForgotPass = findViewById<TextView>(R.id.login_forgot_pass)

        etEmail.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                etEmailInputted = s.toString().trim { it <= ' ' }.isNotEmpty()
            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {
                // TODO
            }
            override fun afterTextChanged(s: Editable) {
                checkValidation()
            }
        })

        etPass.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                etPassInputted = s.toString().trim { it <= ' ' }.isNotEmpty()
            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {
                // TODO
            }
            override fun afterTextChanged(s: Editable) {
                checkValidation()
            }
        })

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPass.text.toString()

            if(email.isNotEmpty()  && password.isNotEmpty()) {
                login_user(email, password)
            } else if (email.isEmpty()) {
                etEmail.error = "Required"
                Toast.makeText(applicationContext,"Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                etPass.error = "Required"
                Toast.makeText(applicationContext,"Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext,"Email atau password salah", Toast.LENGTH_SHORT).show()
            }
        }

        txtRegist.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        txtForgotPass.setOnClickListener{
            val intent = Intent(this, ForgotPassActivity::class.java)
            startActivity(intent)
        }
    }

    fun login_user(email: String, password: String){
        val ardData: APIRequestData = RetroServer.konekRetrofit()!!.create(APIRequestData::class.java)
        val tampilData: Call<LoginResponseModel> = ardData.cekLoginUser("cek_login_user", email, password)
        tampilData.enqueue(object: Callback<LoginResponseModel> {
            override fun onResponse(
                call: Call<LoginResponseModel>,
                response: Response<LoginResponseModel>
            ) {
                val loginStatus = response.body()!!.status
                if (loginStatus){
                    val loginResult = response.body()!!.result
                    val id_user = loginResult[0].id_user
                    val nama_user = loginResult[0].nama_user
                    val username = loginResult[0].username
                    Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT )
                        .show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("id_user",id_user)
                    intent.putExtra("nama_user", nama_user)
                    intent.putExtra("username", username)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(this@LoginActivity, "Login Gagal. Silahkan Cek Ulang Email dan Password!", Toast.LENGTH_LONG)
                        .show()
                }

            }

            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}