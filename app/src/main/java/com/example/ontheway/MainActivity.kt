package com.example.ontheway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginbtn = findViewById<Button>(R.id.btn_customer)
        val registerbtn = findViewById<Button>(R.id.btn_register)

        loginbtn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        registerbtn.setOnClickListener {
            val intent = Intent(this, RegisterAs::class.java)
            startActivity(intent)
        }
    }
}