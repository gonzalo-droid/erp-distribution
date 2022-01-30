package com.example.erp_distribution.di.module

import com.example.erp_distribution.data.repository.DistributionRepository
import com.example.erp_distribution.data.retrofit.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun distributionRepository(apiService: ApiService) = DistributionRepository(apiService)
}