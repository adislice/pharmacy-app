package com.uty.apotekku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textfield.TextInputEditText
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

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
//            val email = etEmail.text.toString()
//            val password = etPass.text.toString()
//
//            if(email == "user" && password == "user") {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
//            } else if (email.isEmpty()) {
//                etEmail.error = "Required"
//                Toast.makeText(applicationContext,"Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
//            } else if (password.isEmpty()) {
//                etPass.error = "Required"
//                Toast.makeText(applicationContext,"Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(applicationContext,"Email atau password salah", Toast.LENGTH_SHORT).show()
//            }
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
}