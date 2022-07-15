package com.example.ontheway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ontheway.databinding.ActivityLoginBinding
import com.example.ontheway.ui.notifications.NotificationsFragment
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.loginbtn.setOnClickListener {
            val userName = binding.etEmail.text.toString()
            val pass = binding.etPw.text.toString()

            if (userName.isNotEmpty() && pass.isNotEmpty()) {
                    firebaseAuth.signInWithEmailAndPassword(userName, pass).addOnCompleteListener{
                        if (it.isSuccessful) {
                            val intent = Intent(this, NotificationsFragment::class.java)
                            startActivity(intent)
                        }else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            }else {
                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_LONG).show()
            }
        }
        binding.tvForgotPw.setOnClickListener {
            val intent = Intent(this, forgot_PW::class.java)
            startActivity(intent)
        }
    }
}