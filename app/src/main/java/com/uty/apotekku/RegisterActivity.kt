package com.uty.apotekku

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.uty.apotekku.API.APIRequestData
import com.uty.apotekku.API.RetroServer
import com.uty.apotekku.Model.LoginResponseModel
import com.uty.apotekku.Model.RegisterResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private var isCbChecked: Boolean = false

    private fun checkValidation(){
        val btnRegist = findViewById<Button>(R.id.regist_btn)
        val etNama = findViewById<TextInputEditText>(R.id.regist_et_nama)
        val etUname = findViewById<TextInputEditText>(R.id.regist_et_uname)
        val etEmail = findViewById<TextInputEditText>(R.id.regist_et_email)
        val etNoTelp = findViewById<TextInputEditText>(R.id.regist_et_notelp)
        val etPass = findViewById<TextInputEditText>(R.id.regist_et_pass)
        val etConfirmPass = findViewById<TextInputEditText>(R.id.regist_et_confirm_pass)
        val nama = etNama.text.toString()
        val uname = etUname.text.toString()
        val email = etEmail.text.toString()
        val notelp = etNoTelp.text.toString()
        val password = etPass.text.toString()
        val passwordConfirm = etConfirmPass.text.toString()

        if(nama.trim().isNotEmpty() &&
            uname.trim().isNotEmpty() &&
            email.trim().isNotEmpty() &&
            notelp.trim().isNotEmpty() &&
            password.trim().isNotEmpty() &&
            passwordConfirm.trim().isNotEmpty() &&
            isCbChecked
        ){
            btnRegist.apply{setBackgroundResource(R.drawable.bg_btn)}
            btnRegist.isEnabled = true
        } else {
            btnRegist.apply{setBackgroundResource(R.drawable.bg_btn_disabled)}
            btnRegist.isEnabled = false
        }
    }

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            when (view.id) {
                R.id.regist_tos -> {
                    if (checked) {
                        isCbChecked = true
                        checkValidation()
                    } else {
                        isCbChecked = false
                        checkValidation()
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etNama = findViewById<TextInputEditText>(R.id.regist_et_nama)
        val etUname = findViewById<TextInputEditText>(R.id.regist_et_uname)
        val etEmail = findViewById<TextInputEditText>(R.id.regist_et_email)
        val etNoTelp = findViewById<TextInputEditText>(R.id.regist_et_notelp)
        val etPass = findViewById<TextInputEditText>(R.id.regist_et_pass)
        val etConfirmPass = findViewById<TextInputEditText>(R.id.regist_et_confirm_pass)
        val btnRegist = findViewById<Button>(R.id.regist_btn)

        etNama.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                // TODO
            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {
                // TODO
            }
            override fun afterTextChanged(s: Editable) {
                checkValidation()
            }
        })

        etEmail.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                // TODO
            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {
                // TODO
            }
            override fun afterTextChanged(s: Editable) {
                checkValidation()
            }
        })

        etNoTelp.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                // TODO
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
                // TODO
            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {
                // TODO
            }
            override fun afterTextChanged(s: Editable) {
                checkValidation()
            }
        })

        etConfirmPass.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                // TODO
            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {
                // TODO
            }
            override fun afterTextChanged(s: Editable) {
                checkValidation()
            }
        })

        btnRegist.setOnClickListener {
            val nama = etNama.text.toString()
            val uname = etUname.text.toString()
            val email = etEmail.text.toString()
            val notelp = etNoTelp.text.toString()
            val password = etPass.text.toString()
            val passwordConfirm = etConfirmPass.text.toString()

            if(nama.isEmpty() &&
               uname.isEmpty() &&
               email.isEmpty() &&
               notelp.isEmpty() &&
               password.isEmpty() &&
               passwordConfirm.isEmpty()
               ) {
               Toast.makeText(applicationContext,"Form harus diisi dengan lengkap", Toast.LENGTH_SHORT).show()
            } else if (nama.isEmpty()) {
                etNama.error = "Nama lengkap tidak boleh kosong"
            } else if (nama.isEmpty()) {
                etUname.error = "Username tidak boleh kosong"
            } else if (email.isEmpty()) {
                etEmail.error = "Email tidak boleh kosong"
            } else if (notelp.isEmpty()) {
                etNoTelp.error = "No. Telepon tidak boleh kosong"
            } else if (password.isEmpty()) {
                Toast.makeText(applicationContext,"Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (passwordConfirm.isEmpty()) {
                Toast.makeText(applicationContext,"Konfirmasi Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (password != passwordConfirm) {
                Toast.makeText(applicationContext,"Konfirmasi Password tidak cocok", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext,"Pendaftaran berhasil", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

//        fun register_user(nama: String, username: String, email: String, notelp: String, password:String){
//            val ardData: APIRequestData = RetroServer.konekRetrofit()!!.create(APIRequestData::class.java)
//            val daftarinData: Call<RegisterResponseModel> = ardData.cekRegisterUser("cek_login_user", nama, username, email, notelp, password)
//            daftarinData.enqueue(new Callback<RegisterResponseModel> {
//                override fun onResponse(
//                    call: Call<RegisterResponseModel>,
//                    response: Response<RegisterResponseModel>
//                ) {
//                    val registerStatus = response.body()!!.status
//                    if (registerStatus){
//                        val registerStatus = response.body()!!.result
//                        val registered_user = registerStatus[0].user_id_terdaftar
//                        val nama_user = registerStatus[0].namaUser
//                        val user_name = registerStatus[0].username
//                        val email_user = registerStatus[0].emailUser
//                        val notelp_user = registerStatus[0].notelpUser
//                        val password_user = registerStatus[0].passwordUser
//
//
//
//                    } else {
//                        Toast.makeText(this@RegisterActivity, "Login Gagal. Silahkan Cek Ulang Email dan Password!", Toast.LENGTH_LONG)
//                            .show()
//                    }
//                }
//                override fun onFailure(call: Call<RegisterResponseModel>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//            })
//        }
    }
}