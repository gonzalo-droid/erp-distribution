package com.solti.inmobiliaria.ui.base

import android.app.Dialog
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.solti.inmobiliaria.R
import com.solti.inmobiliaria.data.Prefs
import com.solti.inmobiliaria.data.response.*
import com.solti.inmobiliaria.di.Orchestrator
import com.solti.inmobiliaria.ui.activity.login.LoginActivity
import com.solti.inmobiliaria.ui.application.ErpApplication
import com.solti.inmobiliaria.utils.PapersManager
import com.solti.inmobiliaria.utils.startActivityTo


@Suppress("PropertyName")
abstract class ErpBaseActivity : BaseActivity() {
    var mLastClickTime: Long = 0
    private var dialogCustom: Dialog? = null

    val FINISH_ACTIVITY = 4678
    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
    }

    protected val component by lazy { Orchestrator.presenterComponent }


    fun getContext() = this

    fun showLoading() {
        hideLoading()
        dialogCustom = Dialog(this)
        dialogCustom!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogCustom!!.setContentView(R.layout.dialog_loading)
        dialogCustom!!.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialogCustom!!.window?.setBackgroundDrawableResource(android.R.color.transparent);
        dialogCustom!!.window?.attributes?.windowAnimations = R.style.AppTheme_Slide

        dialogCustom!!.setCancelable(false)
        dialogCustom!!.show()
    }

    fun hideLoading() {
        if (dialogCustom == null) return
        dialogCustom?.dismiss()
        dialogCustom = null
    }


    fun customWifi() {
        val dialog = Dialog(getContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_wifi)
        val btnWifi: AppCompatButton =
            dialog.findViewById<android.view.View>(R.id.btn_wifi) as AppCompatButton

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.window?.attributes?.windowAnimations = R.style.AppTheme_Slide
        dialog.setCancelable(false)
        dialog.show()
        btnWifi.setOnClickListener {
            dialog.dismiss()
        }
    }


//    fun customTimeOut() {
//        val dialog = Dialog(getContext())
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setContentView(R.layout.dialog_timeout)
//        val btnTimeOut: AppCompatButton =
//            dialog.findViewById<android.view.View>(R.id.btn_timeout) as AppCompatButton
//
//        dialog.window?.setLayout(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
//        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
//        dialog.window?.attributes?.windowAnimations = R.style.AppTheme_Slide
//        dialog.setCancelable(false)
//        dialog.show()
//        btnTimeOut.setOnClickListener {
//            dialog.dismiss()
//        }
//    }

    fun customDialog(title: String, text: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_wifi)
        val btnWifi: AppCompatButton =
            dialog.findViewById<android.view.View>(R.id.btn_wifi) as AppCompatButton


        val txtTitle: AppCompatTextView =
            dialog.findViewById<View>(R.id.lbl_title) as AppCompatTextView

        txtTitle.text = title


        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.window?.attributes?.windowAnimations = R.style.AppTheme_Slide
        dialog.setCancelable(false)
        dialog.show()

        btnWifi.setOnClickListener {
            dialog.dismiss()
        }
    }


    fun toast(text: String) =
        Toast.makeText(this@ErpBaseActivity, text, Toast.LENGTH_LONG).show()

    fun errorMessage(view: TextView, error: String) {
        view.visibility = View.VISIBLE
        view.text = error
        Handler().postDelayed({
            view.text = ""
            view.visibility = View.INVISIBLE
        }, 3000)
    }


     fun logout(prefs: Prefs) {
        ErpApplication.closeAll()
        prefs.logout()
        PapersManager.login.dataCompany = LoginResponse.DataCompany()
        PapersManager.login.dataLogin = LoginResponse.DataLogin()
        PapersManager.login = LoginResponse()
        PapersManager.getArounds = ArrayList<AroundResponse>()
        PapersManager.getTowers = ArrayList<TowerResponse>()
        PapersManager.getLevels = ArrayList<LevelResponse>()
        PapersManager.getProjects = ArrayList<ProjectResponse>()
        finish()
        startActivityTo(LoginActivity::class.java)
    }

    fun customDialogCloseSession(prefs: Prefs) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_close_session)
        val btnAccept: AppCompatButton = dialog.findViewById<View>(R.id.btnOk) as AppCompatButton
        val btnCancel: AppCompatButton =
            dialog.findViewById<View>(R.id.btnCancel) as AppCompatButton

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.window?.attributes?.windowAnimations = R.style.AppTheme_Slide
        dialog.setCancelable(false)
        dialog.show()

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        btnAccept.setOnClickListener {
            logout(prefs)
            dialog.dismiss()
        }
    }


    fun customDialogExpiredSession(prefs: Prefs) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_session_expired)
        val btnAccept: AppCompatButton = dialog.findViewById<View>(R.id.btnOk) as AppCompatButton
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.window?.attributes?.windowAnimations = R.style.AppTheme_Slide
        dialog.setCancelable(false)
        dialog.show()

        btnAccept.setOnClickListener {
            logout(prefs)
            dialog.dismiss()
        }
    }

}