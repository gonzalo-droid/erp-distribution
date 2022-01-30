package com.example.erp_distribution.di.module

import com.example.erp_distribution.di.PresenterScope
import com.example.erp_distribution.feature.distribution.GetListDistributionUseCase
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
//        methods: Methods,
    ) = DistributionPresenter(useCase)

}