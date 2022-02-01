package com.example.erp_distribution.di.module

import com.example.erp_distribution.di.PresenterScope
import com.example.erp_distribution.feature.distribution.*
import com.example.erp_distribution.feature.sale.GetSaleDetailUseCase
import com.example.erp_distribution.presenter.DistributionPresenter
import com.example.erp_distribution.presenter.sale.SaleDetailPresenter
import com.example.erp_distribution.utils.Methods
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
        methods: Methods,
    ) = DistributionPresenter(useCase, useCase1, useCase2, useCase3, useCase4,methods)


    @Provides
    @PresenterScope
    fun saleDetailPresenter(
        useCase: GetSaleDetailUseCase,
        methods: Methods,
    ) = SaleDetailPresenter(useCase,methods)

}