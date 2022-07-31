package com.solti.inmobiliaria.data

import android.content.Context
import android.content.SharedPreferences

class Prefs(private val context: Context) {

    companion object{
        const val FILENAME ="erp"
        const val TOKEN = "token"
        const val PASSWORD = "password"
        const val EMAIL = "email"
        const val URL = "url"
        const val CODE = "code"
        const val NAME_COMPANY = "nameCompany"
        const val SESSION_ERP = "session_erp"

    }

    private val prefs: SharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE)

    var token:String?
        get() = prefs.getString(TOKEN,"")
        set(value) = prefs.edit().putString(TOKEN, value).apply()

    var password:String?
        get() = prefs.getString(PASSWORD,"")
        set(value) = prefs.edit().putString(PASSWORD, value).apply()

    var email:String?
        get() = prefs.getString(EMAIL,"")
        set(value) = prefs.edit().putString(EMAIL, value).apply()

    var url:String?
        get() = prefs.getString(URL,"")
        set(value) = prefs.edit().putString(URL, value).apply()

    var code: String?
        get() = prefs.getString(CODE,"")
        set(value) = prefs.edit().putString(CODE, value).apply()

    var nameCompany:String?
        get() = prefs.getString(NAME_COMPANY,"")
        set(value) = prefs.edit().putString(NAME_COMPANY, value).apply()

    var session:Boolean
        get() = prefs.getBoolean(SESSION_ERP,false)
        set(value) = prefs.edit().putBoolean(SESSION_ERP, value).apply()

    fun logout(){
        session = false
        code = ""
        email = ""
        url = ""
        token = ""
        password = ""
    }
}
