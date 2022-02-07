package com.gonlg.erp_distribution.di.module

import com.gonlg.erp_distribution.di.PresenterScope
import com.gonlg.erp_distribution.feature.distribution.*
import com.gonlg.erp_distribution.feature.login.CodeCompanyUseCase
import com.gonlg.erp_distribution.feature.login.SignInUseCase
import com.gonlg.erp_distribution.feature.sale.GetSaleDetailUseCase
import com.gonlg.erp_distribution.presenter.DistributionPresenter
import com.gonlg.erp_distribution.presenter.LoginPresenter
import com.gonlg.erp_distribution.presenter.sale.SaleDetailPresenter
import com.gonlg.erp_distribution.utils.Methods
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