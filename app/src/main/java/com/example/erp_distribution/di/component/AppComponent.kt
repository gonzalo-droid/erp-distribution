package com.example.erp_distribution.di.component

import android.content.Context
import com.example.erp_distribution.data.retrofit.ApiService
import com.example.erp_distribution.di.module.AppModule
import com.example.erp_distribution.di.module.NetworkModule
import com.example.erp_distribution.di.module.RepositoryModule
import com.example.erp_distribution.di.module.UsesCaseModule
import com.example.erp_distribution.feature.distribution.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, UsesCaseModule::class, RepositoryModule::class])
interface AppComponent {

    fun context(): Context
    fun apiService(): ApiService
    fun getListDistributionUseCase(): GetListDistributionUseCase
    fun getListTowerUseCase(): GetListTowerUseCase
    fun getListLevelUseCase(): GetListLevelUseCase
    fun getListAroundUseCase(): GetListAroundUseCase
    fun getListProjectUseCase(): GetListProjectUseCase


}