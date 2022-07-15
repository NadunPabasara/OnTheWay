package com.example.ontheway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.ontheway.databinding.ActivityMeRegShopLocationBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MeRegShopLocation : AppCompatActivity() {

    private lateinit var binding: ActivityMeRegShopLocationBinding
    val firestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMeRegShopLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.shopLocation.setOnClickListener {
            val merchantInfo = hashMapOf(
                "shop_location" to binding.mapSearchBtn.text.toString()
            )
            //Need to find a method to add previous screen data with this location info to the database.
//            firestore.collection("merchant").document(binding.etEmail.text.toString())
//                .set(merchantInfo)
//                .addOnSuccessListener {
//                    Toast.makeText(this, "Shop location saved successfully", Toast.LENGTH_SHORT).show()
//                }.addOnFailureListener {
//                    Toast.makeText(this, "Failed to add shop location", Toast.LENGTH_SHORT).show()
//                }
        }
        val intent = Intent(this, Map::class.java)
        startActivity(intent)
    }
}