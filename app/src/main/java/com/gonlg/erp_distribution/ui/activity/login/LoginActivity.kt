package com.gonlg.erp_distribution.ui.activity.login

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gonlg.erp_distribution.R
import com.gonlg.erp_distribution.databinding.ActivityLoginBinding
import com.gonlg.erp_distribution.presenter.LoginPresenter
import com.gonlg.erp_distribution.presenter.sale.SaleDetailPresenter
import com.gonlg.erp_distribution.ui.activity.distribution.DistributionActivity
import com.gonlg.erp_distribution.ui.base.ErpBaseActivity
import com.gonlg.erp_distribution.utils.*
import java.io.Serializable
import javax.inject.Inject

class LoginActivity : ErpBaseActivity(), LoginPresenter.View {

    @Inject
    lateinit var loginPresenter: LoginPresenter



    private lateinit var binding: ActivityLoginBinding

    override fun getView(): Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        component.inject(this)
        loginPresenter.attachView(this)

        binding.btnSignIn.setOnClickListener {
            signIn()
        }

        binding.btnValidateCode.setOnClickListener {
            if(!binding.tvCodeCompany.text.isNullOrEmpty()){
                validateCode(binding.tvCodeCompany.text.toString())
            }else{
                Toast.makeText(this,"Debe validar el código de la empresa", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun validateCode(code: String) {

    }

    private fun signIn() {
        startActivityTo(DistributionActivity::class.java)
    }

    override fun loginSuccessPresenter(status: Int, vararg args: Serializable) {
        TODO("Not yet implemented")
    }

    override fun customTimeOut() {
        TODO("Not yet implemented")
    }

    override fun onResume() {
        loginPresenter.attachView(this)
        super.onResume()
    }

    @SuppressLint("MissingPermission")
    override fun onPause() {
        loginPresenter.detachView()
        super.onPause()
    }

}