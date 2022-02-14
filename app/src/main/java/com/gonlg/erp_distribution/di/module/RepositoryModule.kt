package com.gonlg.erp_distribution.di.module

import com.gonlg.erp_distribution.data.Prefs
import com.gonlg.erp_distribution.data.repository.DistributionRepository
import com.gonlg.erp_distribution.data.repository.LoginRepository
import com.gonlg.erp_distribution.data.repository.SaleDetailRepository
import com.gonlg.erp_distribution.data.retrofit.ApiService
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