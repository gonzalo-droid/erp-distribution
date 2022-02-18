package com.solti.solti_inmobiliaria.di

import com.solti.solti_inmobiliaria.di.component.DaggerPresenterComponent
import com.solti.solti_inmobiliaria.di.component.PresenterComponent
import com.solti.solti_inmobiliaria.ui.application.ErpApplication

object Orchestrator {

    val presenterComponent: PresenterComponent by lazy {
        DaggerPresenterComponent
            .builder()
            .appComponent(ErpApplication.appComponent)
            .build()
    }
}