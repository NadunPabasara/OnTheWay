package com.example.ontheway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ontheway.databinding.ActivityCustomerRegBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CustomerReg : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerRegBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustomerRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.button.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val pNumber = binding.pNumber.text.toString()
            val pass = binding.passWord.text.toString()
            val confirmPass = binding.confirmPass.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Customer")
            val customer = Customer(email,pNumber,pass,confirmPass)
            database.child(email).setValue(customer).addOnSuccessListener {

                binding.etEmail.text?.clear()
                binding.pNumber.text?.clear()
                binding.passWord.text?.clear()
                binding.confirmPass.text?.clear()

                Toast.makeText(this, "successfully Saved", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

            }
            if (email.isNotEmpty() && pNumber.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
                        if (it.isSuccessful) {
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