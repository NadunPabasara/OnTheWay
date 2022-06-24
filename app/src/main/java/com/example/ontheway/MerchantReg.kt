package com.example.ontheway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.ontheway.databinding.ActivityMerchantRegBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MerchantReg : AppCompatActivity() {

    private lateinit var binding: ActivityMerchantRegBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMerchantRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.button.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val pNumber = binding.pNumber.text.toString()
            val pass = binding.pWord.text.toString()
            val confirmPass = binding.cpWord.text.toString()

            if (email.isNotEmpty() && pNumber.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
                        if (it.isSuccessful) {
                        }else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_LONG).show()
            }

            database = FirebaseDatabase.getInstance().getReference("Merchant")

            val merchant = Merchant(email,pNumber,pass)
            database.child(email).setValue(merchant).addOnSuccessListener {
                Toast.makeText(this, "successfully Saved", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
            }
        }

        val nextBtn = findViewById<Button>(R.id.button)


        nextBtn.setOnClickListener {
            val intent = Intent(this, MeRegShopLocation::class.java)
            intent.putExtra("email","email")
            startActivity(intent)

            binding.etEmail.text?.clear()
            binding.pNumber.text?.clear()
            binding.pWord.text?.clear()

        }
    }
}