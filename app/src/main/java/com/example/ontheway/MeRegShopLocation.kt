package com.example.ontheway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.type.LatLng

class MeRegShopLocation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_me_reg_shop_location)

        val shopLocation = findViewById<EditText>(R.id.etName)
        val searchBtn = findViewById<Button>(R.id.mapSearch)
        val saveBtn = findViewById<Button>(R.id.Save)

        searchBtn.setOnClickListener {
            val intent = Intent(this, Map::class.java)
            startActivity(intent)
        }
    }
}