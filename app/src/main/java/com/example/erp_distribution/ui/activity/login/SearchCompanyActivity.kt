package com.example.erp_distribution.ui.activity.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.erp_distribution.R
import com.example.erp_distribution.databinding.ActivityLoginBinding
import com.example.erp_distribution.databinding.ActivitySearchCompanyBinding
import com.example.erp_distribution.ui.activity.distribution.DistributionActivity
import com.example.erp_distribution.utils.startActivityTo

class SearchCompanyActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchCompanyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSignIn.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        startActivityTo(LoginActivity::class.java)
    }
}