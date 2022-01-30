package com.example.erp_distribution.utils

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

fun AppCompatActivity.startActivityTo(cls: Class<out AppCompatActivity>, vararg extras: Serializable) {
    val intent = Intent(this, cls)

    for (i in extras.indices) {
        intent.putExtra("extra$i", extras[i])
    }

    startActivity(intent)
    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun LayoutInflater.inflate(@LayoutRes layoutRes: Int): View = inflate(layoutRes, null)
