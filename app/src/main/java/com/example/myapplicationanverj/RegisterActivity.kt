package com.example.myapplicationanverj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {
    lateinit var btnRegister : Button
    lateinit var etEmail : EditText
    lateinit var etPassword : EditText
    lateinit var tv_Log_in : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        etEmail = findViewById(R.id.et_login_email)
        etPassword=findViewById(R.id.et_login_password)
        btnRegister = findViewById(R.id.btnRegister)
        tv_Log_in = findViewById(R.id.tvLogin)
        tv_Log_in.setOnClickListener {
            startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
        }
        btnRegister.setOnClickListener {
            when{
                TextUtils.isEmpty(etEmail.text.toString().trim{it<=' '})->{
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(etPassword.text.toString().trim{it<=' '})->{
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else->{
                    val email:String= etEmail.text.toString().trim{it<=' '}
                    val password:String= etPassword.text.toString().trim{it<=' '}

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val firebaseUser: FirebaseUser = task.result!!.user!!
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "You are registered successfully.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent =
                                    Intent(this@RegisterActivity, MainActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", firebaseUser.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }

        }

    }

}