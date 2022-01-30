package com.example.erp_distribution.di.module

import com.example.erp_distribution.data.repository.DistributionRepository
import com.example.erp_distribution.feature.distribution.GetListDistributionUseCase
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class UsesCaseModule {

    @Provides
    @Singleton
    fun getListDistributionUseCase(
        repository: DistributionRepository,
        @Named("executor_thread") executorThread: Scheduler,
        @Named("ui_thread") uiThread: Scheduler
    ) = GetListDistributionUseCase(executorThread, uiThread, repository)


    /** always in bottom */
    @Provides
    @Named("executor_thread")
    fun provideExecutorThread(): Scheduler = Schedulers.io()

    @Provides
    @Named("ui_thread")
    fun provideUiThread(): Scheduler = AndroidSchedulers.mainThread()
}