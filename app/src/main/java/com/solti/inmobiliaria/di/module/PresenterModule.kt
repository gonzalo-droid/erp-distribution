package com.solti.inmobiliaria.di.module

import com.solti.inmobiliaria.di.PresenterScope
import com.solti.inmobiliaria.feature.distribution.*
import com.solti.inmobiliaria.feature.login.CodeCompanyUseCase
import com.solti.inmobiliaria.feature.login.SignInUseCase
import com.solti.inmobiliaria.feature.sale.GetSaleDetailUseCase
import com.solti.inmobiliaria.presenter.DistributionPresenter
import com.solti.inmobiliaria.presenter.LoginPresenter
import com.solti.inmobiliaria.presenter.sale.SaleDetailPresenter
import com.solti.inmobiliaria.utils.Methods
import dagger.Module
import dagger.Provides

@Module
@PresenterScope
class PresenterModule {

    @Provides
    @PresenterScope
    fun distributionPresenter(
        getListDistributionUseCase: GetListDistributionUseCase,
        getListAroundUseCase: GetListAroundUseCase,
        getListTowerUseCase: GetListTowerUseCase,
        getListLevelUseCase: GetListLevelUseCase,
        getListProjectUseCase: GetListProjectUseCase,
        methods: Methods,
    ) = DistributionPresenter(
        getListDistributionUseCase,
        getListAroundUseCase,
        getListTowerUseCase,
        getListLevelUseCase,
        getListProjectUseCase,
        methods
    )


    @Provides
    @PresenterScope
    fun saleDetailPresenter(
        getSaleDetailUseCase: GetSaleDetailUseCase,
        methods: Methods,
    ) = SaleDetailPresenter(
        getSaleDetailUseCase,
        methods
    )


    @Provides
    @PresenterScope
    fun loginPresenter(
        signInUseCase: SignInUseCase,
        codeCompanyUseCase: CodeCompanyUseCase,
        methods: Methods,
    ) = LoginPresenter(
        signInUseCase,
        codeCompanyUseCase,
        methods
    )

}