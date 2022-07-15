package com.example.ontheway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ontheway.databinding.ActivityMerchantRegBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MerchantReg : AppCompatActivity() {

    private lateinit var binding: ActivityMerchantRegBinding
    private lateinit var firebaseAuth: FirebaseAuth
    val firestore = Firebase.firestore

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
                            val customerInfo = hashMapOf(
                                "email" to binding.etEmail.text.toString(),
                                "phone_number" to binding.etEmail.text.toString(),
                                "password" to binding.pWord.text.toString()
                            )
                            firestore.collection("merchants").document(binding.etEmail.text.toString())
                                .set(customerInfo)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "You added as a merchant", Toast.LENGTH_SHORT).show()
                                }.addOnFailureListener {
                                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
                                }
                            val intent = Intent(this, Login::class.java)
                            startActivity(intent)
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
        }
    }
}