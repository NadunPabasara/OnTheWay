package com.example.ontheway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MerchantReg : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_reg)

        val nextbtn = findViewById<Button>(R.id.button)

        nextbtn.setOnClickListener {
            val intent = Intent(this, MeRegShopLocation::class.java)
            startActivity(intent)
        }
    }
}