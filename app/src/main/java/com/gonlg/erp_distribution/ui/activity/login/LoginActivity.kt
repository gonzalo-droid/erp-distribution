package com.gonlg.erp_distribution.ui.activity.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.net.UrlQuerySanitizer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.gonlg.erp_distribution.R
import com.gonlg.erp_distribution.data.request.CodeCompanyRequest
import com.gonlg.erp_distribution.data.request.DistributionIdRequest
import com.gonlg.erp_distribution.data.request.LoginRequest
import com.gonlg.erp_distribution.data.response.CodeCompanyResponse
import com.gonlg.erp_distribution.data.response.DistributionResponse
import com.gonlg.erp_distribution.data.response.LoginResponse
import com.gonlg.erp_distribution.data.response.SaleDetailResponse
import com.gonlg.erp_distribution.databinding.ActivityLoginBinding
import com.gonlg.erp_distribution.presenter.LoginPresenter
import com.gonlg.erp_distribution.presenter.sale.SaleDetailPresenter
import com.gonlg.erp_distribution.ui.activity.distribution.DistributionActivity
import com.gonlg.erp_distribution.ui.base.ErpBaseActivity
import com.gonlg.erp_distribution.utils.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.io.Serializable
import javax.inject.Inject

class LoginActivity : ErpBaseActivity(), LoginPresenter.View {

    @Inject
    lateinit var loginPresenter: LoginPresenter

    var activeInputCode: Boolean = false
    private lateinit var binding: ActivityLoginBinding

    override fun getView(): Int = R.layout.activity_login


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        component.inject(this)
        loginPresenter.attachView(this)

        binding.btnSignIn.setOnClickListener {
            if(!PapersManager.responseCodeCompany.codeCompany.url.equals("")
                && !binding.etEmail.text.isNullOrEmpty()
                && !binding.etPassword.text.isNullOrEmpty()){
                signIn()
            }else{
                Toast.makeText(this,"Datos incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
        enableFiled(false)

        binding.btnValidateCode.setOnClickListener {

            if(activeInputCode){
                if(!binding.edCodeCompany.text.isNullOrEmpty()){
                    validateCode(binding.edCodeCompany.text.toString())
                }else{
                    Toast.makeText(this,"Debe ingresar el código de la empresa", Toast.LENGTH_SHORT).show()
                }
            }else {
                activeInputCode = true
                binding.edCodeCompany.isEnabled=true
                enableFiled(false)
            }

        }

    }

    private fun validateCode(code: String) {

        loginPresenter.codeCompanyUseCase(CodeCompanyRequest().apply {
            this.code = code
        },listener = { i, response  ->
            when (i) {
                200 -> {
                    var data = response as CodeCompanyResponse
                    if(data.success.equals("Y")){
                        PapersManager.responseCodeCompany = data
                        PapersManager.urlBase = PapersManager.responseCodeCompany.codeCompany.url
                        enableFiled(true)
                        activeInputCode = false
                        binding.edCodeCompany.isEnabled=false
                        Toast.makeText(this,"Código correcto", Toast.LENGTH_SHORT).show()
                    }else{
                        enableFiled(false)

                        Toast.makeText(this,"Código incorrecto", Toast.LENGTH_SHORT).show()
                    }

                }
                else -> {
                    Toast.makeText(this,"Información no encontrada", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun enableFiled(value:Boolean){
        Log.d("value", value.toString())

        binding.etEmail.setText("")
        binding.etPassword.setText("")

        binding.etEmail.isEnabled = value
        binding.etPassword.isEnabled = value
        if(value){
            binding.btnValidateCode.setImageResource(R.drawable.ic_close)
            binding.btnSignIn.backgroundTintList = ColorStateList.valueOf(this.getColor(R.color.teal_700))
        }else{
            binding.btnValidateCode.setImageResource(R.drawable.ic_check)
            binding.btnSignIn.backgroundTintList = ColorStateList.valueOf(this.getColor(R.color.teal_block))
        }
        binding.btnSignIn.isEnabled = value

    }
    private fun signIn() {
        loginPresenter.signInUser(LoginRequest().apply {
            this.email = binding.etEmail.text.toString()
            this.password = binding.etPassword.text.toString()
        })
    }

    override fun loginSuccessPresenter(status: Int, vararg args: Serializable) {
        when (status) {
            200 -> {
                val response = args[0] as LoginResponse
                if(response.success){
                    PapersManager.token = "Bearer ${response.token}"
                    Toast.makeText(this,"success", Toast.LENGTH_SHORT).show()
                    startActivityTo(DistributionActivity::class.java)
                }else{
                    Toast.makeText(this,"Incorrecto email o contraseña", Toast.LENGTH_SHORT).show()
                }
            }
            else -> {
                Toast.makeText(this,"Información incorrecta", Toast.LENGTH_SHORT).show()
            }
        }
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