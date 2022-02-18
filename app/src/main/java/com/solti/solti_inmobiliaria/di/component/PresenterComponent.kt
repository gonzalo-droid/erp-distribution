package com.solti.solti_inmobiliaria.di.component

import com.solti.solti_inmobiliaria.di.PresenterScope
import com.solti.solti_inmobiliaria.di.module.PresenterModule
import com.solti.solti_inmobiliaria.ui.activity.distribution.DistributionActivity
import com.solti.solti_inmobiliaria.ui.activity.login.LoginActivity
import dagger.Component

@PresenterScope
@Component(dependencies = [AppComponent::class], modules = [PresenterModule::class])
interface PresenterComponent {
    fun inject(activity: DistributionActivity)
    fun inject(activity: LoginActivity)
}
