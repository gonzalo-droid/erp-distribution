package com.example.erp_distribution.di.module

import com.example.erp_distribution.di.PresenterScope
import com.example.erp_distribution.feature.distribution.*
import com.example.erp_distribution.presenter.DistributionPresenter
import dagger.Module
import dagger.Provides

@Module
@PresenterScope
class PresenterModule {

    @Provides
    @PresenterScope
    fun distributionPresenter(
        useCase: GetListDistributionUseCase,
        useCase1: GetListAroundUseCase,
        useCase2: GetListTowerUseCase,
        useCase3: GetListLevelUseCase,
        useCase4: GetListProjectUseCase,
//        methods: Methods,
    ) = DistributionPresenter(useCase, useCase1, useCase2, useCase3, useCase4)

}