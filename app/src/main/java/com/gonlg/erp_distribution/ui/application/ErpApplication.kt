package com.gonlg.erp_distribution.ui.application

import android.app.Activity
import android.app.Application
import com.gonlg.erp_distribution.di.component.AppComponent
import com.gonlg.erp_distribution.di.component.DaggerAppComponent
import com.gonlg.erp_distribution.di.module.AppModule
import com.gonlg.erp_distribution.utils.Methods
import io.paperdb.Paper
import java.util.*

open class ErpApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
        Methods.init(this)

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