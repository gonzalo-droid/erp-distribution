package com.solti.inmobiliaria.di.component

import com.solti.inmobiliaria.di.PresenterScope
import com.solti.inmobiliaria.di.module.PresenterModule
import com.solti.inmobiliaria.ui.activity.distribution.DistributionActivity
import com.solti.inmobiliaria.ui.activity.login.LoginActivity
import dagger.Component

@PresenterScope
@Component(dependencies = [AppComponent::class], modules = [PresenterModule::class])
interface PresenterComponent {
    fun inject(activity: DistributionActivity)
    fun inject(activity: LoginActivity)
}
