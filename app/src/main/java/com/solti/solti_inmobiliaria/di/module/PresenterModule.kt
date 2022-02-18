package com.solti.solti_inmobiliaria.di.module

import com.solti.solti_inmobiliaria.di.PresenterScope
import com.solti.solti_inmobiliaria.feature.distribution.*
import com.solti.solti_inmobiliaria.feature.login.CodeCompanyUseCase
import com.solti.solti_inmobiliaria.feature.login.SignInUseCase
import com.solti.solti_inmobiliaria.feature.sale.GetSaleDetailUseCase
import com.solti.solti_inmobiliaria.presenter.DistributionPresenter
import com.solti.solti_inmobiliaria.presenter.LoginPresenter
import com.solti.solti_inmobiliaria.presenter.sale.SaleDetailPresenter
import com.solti.solti_inmobiliaria.utils.Methods
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