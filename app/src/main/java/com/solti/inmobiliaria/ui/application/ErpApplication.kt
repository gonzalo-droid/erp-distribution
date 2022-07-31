package com.solti.inmobiliaria.ui.application

import android.app.Activity
import android.app.Application
import com.solti.inmobiliaria.data.Prefs
import com.solti.inmobiliaria.di.component.AppComponent
import com.solti.inmobiliaria.di.component.DaggerAppComponent
import com.solti.inmobiliaria.di.module.AppModule
import com.solti.inmobiliaria.utils.Methods
import io.paperdb.Paper
import timber.log.Timber
import java.util.*

open class ErpApplication : Application() {

    lateinit var prefs: Prefs

    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
        Methods.init(this)
        prefs = Prefs(applicationContext)

        Timber.plant(Timber.DebugTree())


        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

    }

    companion object {
        lateinit var appComponent: AppComponent

        var countShow = 0

        private val activities = Stack<Activity>()



        fun lastActivities() : Activity? {
            return activities.last()
        }

        fun exitsActivities() : Boolean {
            return activities.isEmpty()
        }

        fun addActivity(activity: Activity) {
            activities.add(activity)
        }

        fun removeActivity(activity: Activity) {
            activities.remove(activity)
        }

        fun closeAll() {
            for (activity in activities) {
                try {
                    activity.finish()
                } catch (ignore: Exception) {
                }
            }
            activities.clear()
        }

    }

}