package com.gonlg.erp_distribution.di.module

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
    fun distributionRepository(apiService: ApiService) = DistributionRepository(apiService)

    @Provides
    @Singleton
    fun saleDetailRepository(apiService: ApiService) = SaleDetailRepository(apiService)

    @Provides
    @Singleton
    fun loginRepository(apiService: ApiService) = LoginRepository(apiService)
}