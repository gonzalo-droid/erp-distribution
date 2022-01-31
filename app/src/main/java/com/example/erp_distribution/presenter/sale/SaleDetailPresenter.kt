package com.example.erp_distribution.presenter.sale

import com.example.erp_distribution.feature.sale.GetSaleDetailUseCase
import com.example.erp_distribution.presenter.base.BasePresenter
import java.io.Serializable

class SaleDetailPresenter (
    private var getSaleDetailUseCase: GetSaleDetailUseCase,



): BasePresenter<SaleDetailPresenter.View>() {

    fun getSaleDetailUseCase(){

    }

    interface View : BasePresenter.View {
        fun saleDetailSuccessPresenter(status: Int, vararg args: Serializable)
    }
}