package com.solti.solti_inmobiliaria.ui.activity.login

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.solti.solti_inmobiliaria.R
import com.solti.solti_inmobiliaria.data.Prefs
import com.solti.solti_inmobiliaria.data.request.CodeCompanyRequest
import com.solti.solti_inmobiliaria.data.request.LoginRequest
import com.solti.solti_inmobiliaria.data.response.CodeCompanyResponse
import com.solti.solti_inmobiliaria.data.response.LoginResponse
import com.solti.solti_inmobiliaria.databinding.ActivityLoginBinding
import com.solti.solti_inmobiliaria.presenter.LoginPresenter
import com.solti.solti_inmobiliaria.ui.activity.distribution.DistributionActivity
import com.solti.solti_inmobiliaria.ui.base.ErpBaseActivity
import com.solti.solti_inmobiliaria.utils.*
import io.paperdb.BuildConfig
import io.paperdb.Paper
import java.io.Serializable
import javax.inject.Inject

class LoginActivity : ErpBaseActivity(), LoginPresenter.View {

    @Inject
    lateinit var loginPresenter: LoginPresenter

    @Inject
    lateinit var prefs: Prefs

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
            if(!binding.etEmail.text.isNullOrEmpty()
                && !binding.etCodeCompany.text.isNullOrEmpty()
                && !binding.etPassword.text.isNullOrEmpty()){
                signIn()
            }else{
                Toast.makeText(this,"Datos incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        Paper.book(BuildConfig.FLAVOR).delete("login")
        if(prefs.session){
            startActivityTo(DistributionActivity::class.java)
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
                        PapersManager.login.dataCompany.url = PapersManager.responseCodeCompany.codeCompany.url
//                        enableFiled(true)
                        activeInputCode = false
                        binding.etCodeCompany.isEnabled=false
                        Toast.makeText(this,"Código correcto", Toast.LENGTH_SHORT).show()
                    }else{
//                        enableFiled(false)

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
            this.code = binding.etCodeCompany.text.toString()
            this.email = binding.etEmail.text.toString()
            this.password = binding.etPassword.text.toString()
        })
    }

    override fun loginSuccessPresenter(status: Int, vararg args: Serializable) {
        when (status) {
            200 -> {
                val response = args[0] as LoginResponse
                if(response.success.equals("Y")){
                    if(response.dataLogin.success){
                        Log.d("url-->",response.dataCompany.url )

                        prefs.email = binding.etEmail.text.toString()
                        prefs.password = binding.etPassword.text.toString()
                        prefs.token = "Bearer ${response.dataLogin.token}"
                        prefs.url = response.dataCompany.url
                        prefs.nameCompany = response.dataCompany.name
                        prefs.session = true

                        finish()
                        startActivityTo(DistributionActivity::class.java)

                    }else{
                        Toast.makeText(this,"Email o contraseña son incorrectos", Toast.LENGTH_SHORT).show()
                    }

                }else{
                    Toast.makeText(this,"Código incorrecto", Toast.LENGTH_SHORT).show()
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