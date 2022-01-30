package com.example.erp_distribution.ui.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.Configuration
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.erp_distribution.ui.application.ErpApplication
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textfield.TextInputLayout

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