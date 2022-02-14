package com.gonlg.erp_distribution.ui.base

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.gonlg.erp_distribution.ui.application.ErpApplication

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*firebaseAnalytics = Firebase.analytics*/
        setContentView(getView())
        onCreate()
        ErpApplication.addActivity(this)
        Log.d("onCreate", "____________________________________");
    }

    @SuppressLint("MissingSuperCall")
    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "____________________________________");
        ErpApplication.removeActivity(this)
    }



    fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = activity.currentFocus ?: View(activity)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onResume() {
        super.onResume()
    }


    @SuppressLint("RtlHardcoded")
    override fun onBackPressed() {
        super.onBackPressed()
    }
    abstract fun getView(): Int

    open fun onCreate() {}


}