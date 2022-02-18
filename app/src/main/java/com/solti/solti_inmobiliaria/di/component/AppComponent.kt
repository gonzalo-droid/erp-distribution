package com.solti.solti_inmobiliaria.di.component

import android.content.Context
import com.solti.solti_inmobiliaria.data.Prefs
import com.solti.solti_inmobiliaria.data.retrofit.ApiService
import com.solti.solti_inmobiliaria.di.module.*
import com.solti.solti_inmobiliaria.feature.distribution.*
import com.solti.solti_inmobiliaria.feature.login.CodeCompanyUseCase
import com.solti.solti_inmobiliaria.feature.login.SignInUseCase
import com.solti.solti_inmobiliaria.feature.sale.GetSaleDetailUseCase
import com.solti.solti_inmobiliaria.utils.Methods
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, PrefsModule::class, MethodsModule::class, NetworkModule::class, UsesCaseModule::class, RepositoryModule::class])
interface AppComponent {

    fun context(): Context
    fun apiService(): ApiService
    fun methods(): Methods
    fun prefs(): Prefs

    fun getListDistributionUseCase(): GetListDistributionUseCase
    fun getListTowerUseCase(): GetListTowerUseCase
    fun getListLevelUseCase(): GetListLevelUseCase
    fun getListAroundUseCase(): GetListAroundUseCase
    fun getListProjectUseCase(): GetListProjectUseCase

    fun getSaleDetailUseCase(): GetSaleDetailUseCase

    fun signInUseCase(): SignInUseCase
    fun codeCompanyUseCase(): CodeCompanyUseCase


}