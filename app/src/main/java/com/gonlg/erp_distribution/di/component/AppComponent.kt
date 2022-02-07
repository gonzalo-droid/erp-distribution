package com.gonlg.erp_distribution.di.component

import android.content.Context
import com.gonlg.erp_distribution.data.retrofit.ApiService
import com.gonlg.erp_distribution.di.module.*
import com.gonlg.erp_distribution.feature.distribution.*
import com.gonlg.erp_distribution.feature.login.CodeCompanyUseCase
import com.gonlg.erp_distribution.feature.login.SignInUseCase
import com.gonlg.erp_distribution.feature.sale.GetSaleDetailUseCase
import com.gonlg.erp_distribution.utils.Methods
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, MethodsModule::class, NetworkModule::class, UsesCaseModule::class, RepositoryModule::class])
interface AppComponent {

    fun context(): Context
    fun apiService(): ApiService
    fun methods(): Methods

    fun getListDistributionUseCase(): GetListDistributionUseCase
    fun getListTowerUseCase(): GetListTowerUseCase
    fun getListLevelUseCase(): GetListLevelUseCase
    fun getListAroundUseCase(): GetListAroundUseCase
    fun getListProjectUseCase(): GetListProjectUseCase

    fun getSaleDetailUseCase(): GetSaleDetailUseCase

    fun signInUseCase(): SignInUseCase
    fun codeCompanyUseCase(): CodeCompanyUseCase


}