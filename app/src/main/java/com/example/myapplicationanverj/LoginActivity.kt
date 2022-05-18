package com.example.myapplicationanverj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    lateinit var tv_sign_up : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tv_sign_up = findViewById(R.id.tvSignup)
        tv_sign_up.setOnClickListener {
            startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
        }
    }
}
