package com.solti.inmobiliaria.di

import com.solti.inmobiliaria.di.component.DaggerPresenterComponent
import com.solti.inmobiliaria.di.component.PresenterComponent
import com.solti.inmobiliaria.ui.application.ErpApplication

object Orchestrator {

    val presenterComponent: PresenterComponent by lazy {
        DaggerPresenterComponent
            .builder()
            .appComponent(ErpApplication.appComponent)
            .build()
    }
}