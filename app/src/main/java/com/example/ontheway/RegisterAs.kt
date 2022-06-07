package com.example.ontheway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegisterAs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_as)

        val asCustomer = findViewById<Button>(R.id.btn_customer)
        val asMerchant = findViewById<Button>(R.id.btn_merchant)

        asCustomer.setOnClickListener {
            val intent = Intent(this, CustomerReg::class.java)
            startActivity(intent)
        }
        asMerchant.setOnClickListener {
            val intent = Intent(this, MerchantReg::class.java)
            startActivity(intent)
        }
    }
}