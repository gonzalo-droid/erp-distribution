package com.solti.inmobiliaria.di.module

import com.solti.inmobiliaria.data.Prefs
import com.solti.inmobiliaria.data.repository.DistributionRepository
import com.solti.inmobiliaria.data.repository.LoginRepository
import com.solti.inmobiliaria.data.repository.SaleDetailRepository
import com.solti.inmobiliaria.data.retrofit.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun distributionRepository(apiService: ApiService,prefs: Prefs) = DistributionRepository(apiService,prefs)

    @Provides
    @Singleton
    fun saleDetailRepository(apiService: ApiService,prefs: Prefs) = SaleDetailRepository(apiService,prefs)

    @Provides
    @Singleton
    fun loginRepository(apiService: ApiService,prefs: Prefs) = LoginRepository(apiService,prefs)
}