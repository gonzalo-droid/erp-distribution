package com.gonlg.erp_distribution.di

import com.gonlg.erp_distribution.di.component.DaggerPresenterComponent
import com.gonlg.erp_distribution.di.component.PresenterComponent
import com.gonlg.erp_distribution.ui.application.ErpApplication

object Orchestrator {

    val presenterComponent: PresenterComponent by lazy {
        DaggerPresenterComponent
            .builder()
            .appComponent(ErpApplication.appComponent)
            .build()
    }
}