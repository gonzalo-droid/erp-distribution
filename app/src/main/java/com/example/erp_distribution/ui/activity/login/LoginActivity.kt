package com.example.erp_distribution.ui.activity.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.erp_distribution.databinding.ActivityLoginBinding
import com.example.erp_distribution.ui.activity.distribution.DistributionActivity
import com.example.erp_distribution.utils.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSignIn.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        startActivityTo(DistributionActivity::class.java)
    }
}