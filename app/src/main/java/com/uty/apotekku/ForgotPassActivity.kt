package com.uty.apotekku

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.Toast

class ForgotPassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

        val forgotet = findViewById<TextInputEditText>(R.id.forgot_et)
        val forgotbtn = findViewById<Button>(R.id.forgot_btn)

        forgotet.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                if (s.toString().trim { it <= ' ' }.isEmpty())
                {
                    forgotbtn.apply{setBackgroundResource(R.drawable.bg_btn_disabled)}
                    forgotbtn.isEnabled = false
                }
                else
                {
                    forgotbtn.apply{setBackgroundResource(R.drawable.bg_btn)}
                    forgotbtn.isEnabled = true
                }
            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {
                // TODO
            }
            override fun afterTextChanged(s: Editable) {
                // TODO
            }
        })

        forgotbtn.setOnClickListener{
            val emailnope = forgotet.text.toString()

            if(emailnope.isEmpty()) {
                forgotet.error = "Form harus diisi"
            } else {
                Toast.makeText(applicationContext,"Permintaan telah dikirim, cek Email atau SMS untuk mengakses link reset kata sandi", Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}