package com.solti.inmobiliaria.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log

class Methods(private val context: Context) {

    @SuppressLint("NewApi")
    fun isInternetConnected(): Boolean {
        var isConnected = false
        val cm = methods?.context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    isConnected = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        isConnected = this.isConnectedOrConnecting
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        isConnected = this.isConnectedOrConnecting
                    }
                }
            }
        }
        Log.d("tag-connected", isConnected.toString())
        return isConnected
    }


    companion object {

        @SuppressLint("StaticFieldLeak")
        private var methods: Methods? = null

        fun init(context: Context) {
            methods = Methods(context)
        }
    }

}