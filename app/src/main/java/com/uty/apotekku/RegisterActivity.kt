package com.uty.apotekku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textfield.TextInputEditText
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    private var isCbChecked: Boolean = false

    private fun checkValidation(){
        val btnRegist = findViewById<Button>(R.id.regist_btn)
        val etNama = findViewById<TextInputEditText>(R.id.regist_et_nama)
        val etEmail = findViewById<TextInputEditText>(R.id.regist_et_email)
        val etNoTelp = findViewById<TextInputEditText>(R.id.regist_et_notelp)
        val etPass = findViewById<TextInputEditText>(R.id.regist_et_pass)
        val etConfirmPass = findViewById<TextInputEditText>(R.id.regist_et_confirm_pass)
        val nama = etNama.text.toString()
        val email = etEmail.text.toString()
        val notelp = etNoTelp.text.toString()
        val password = etPass.text.toString()
        val passwordConfirm = etConfirmPass.text.toString()

        if(nama.trim().isNotEmpty() &&
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
            val email = etEmail.text.toString()
            val notelp = etNoTelp.text.toString()
            val password = etPass.text.toString()
            val passwordConfirm = etConfirmPass.text.toString()

            if(nama.isEmpty() &&
               email.isEmpty() &&
               notelp.isEmpty() &&
               password.isEmpty() &&
               passwordConfirm.isEmpty()
               ) {
               Toast.makeText(applicationContext,"Form harus diisi dengan lengkap", Toast.LENGTH_SHORT).show()
            } else if (nama.isEmpty()) {
                etNama.error = "Nama tidak boleh kosong"
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
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}