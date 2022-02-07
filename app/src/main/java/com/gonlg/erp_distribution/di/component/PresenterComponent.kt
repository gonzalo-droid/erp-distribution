package com.gonlg.erp_distribution.di.component

import com.gonlg.erp_distribution.di.PresenterScope
import com.gonlg.erp_distribution.di.module.PresenterModule
import com.gonlg.erp_distribution.ui.activity.distribution.DistributionActivity
import com.gonlg.erp_distribution.ui.activity.login.LoginActivity
import dagger.Component

@PresenterScope
@Component(dependencies = [AppComponent::class], modules = [PresenterModule::class])
interface PresenterComponent {
    fun inject(activity: DistributionActivity)
    fun inject(activity: LoginActivity)
}
