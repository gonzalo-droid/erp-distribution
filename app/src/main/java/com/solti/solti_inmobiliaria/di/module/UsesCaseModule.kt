package com.solti.solti_inmobiliaria.di.module

import com.solti.solti_inmobiliaria.data.repository.DistributionRepository
import com.solti.solti_inmobiliaria.data.repository.LoginRepository
import com.solti.solti_inmobiliaria.data.repository.SaleDetailRepository
import com.solti.solti_inmobiliaria.feature.distribution.*
import com.solti.solti_inmobiliaria.feature.login.CodeCompanyUseCase
import com.solti.solti_inmobiliaria.feature.login.SignInUseCase
import com.solti.solti_inmobiliaria.feature.sale.GetSaleDetailUseCase
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

    @Provides
    @Singleton
    fun getListAroundUseCase(
        repository: DistributionRepository,
        @Named("executor_thread") executorThread: Scheduler,
        @Named("ui_thread") uiThread: Scheduler
    ) = GetListAroundUseCase(executorThread, uiThread, repository)

    @Provides
    @Singleton
    fun getListLevelUseCase(
        repository: DistributionRepository,
        @Named("executor_thread") executorThread: Scheduler,
        @Named("ui_thread") uiThread: Scheduler
    ) = GetListLevelUseCase(executorThread, uiThread, repository)


    @Provides
    @Singleton
    fun getListTowerUseCase(
        repository: DistributionRepository,
        @Named("executor_thread") executorThread: Scheduler,
        @Named("ui_thread") uiThread: Scheduler
    ) = GetListTowerUseCase(executorThread, uiThread, repository)

    @Provides
    @Singleton
    fun getListProjectUseCase(
        repository: DistributionRepository,
        @Named("executor_thread") executorThread: Scheduler,
        @Named("ui_thread") uiThread: Scheduler
    ) = GetListProjectUseCase(executorThread, uiThread, repository)

    @Provides
    @Singleton
    fun getSaleDetailUseCase(
        repository: SaleDetailRepository,
        @Named("executor_thread") executorThread: Scheduler,
        @Named("ui_thread") uiThread: Scheduler
    ) = GetSaleDetailUseCase(executorThread, uiThread, repository)

    @Provides
    @Singleton
    fun signInUseCase(
        repository: LoginRepository,
        @Named("executor_thread") executorThread: Scheduler,
        @Named("ui_thread") uiThread: Scheduler
    ) = SignInUseCase(executorThread, uiThread, repository)

    @Provides
    @Singleton
    fun codeCompanyUseCase(
        repository: LoginRepository,
        @Named("executor_thread") executorThread: Scheduler,
        @Named("ui_thread") uiThread: Scheduler
    ) = CodeCompanyUseCase(executorThread, uiThread, repository)

    /** always in bottom */
    @Provides
    @Named("executor_thread")
    fun provideExecutorThread(): Scheduler = Schedulers.io()

    @Provides
    @Named("ui_thread")
    fun provideUiThread(): Scheduler = AndroidSchedulers.mainThread()
}