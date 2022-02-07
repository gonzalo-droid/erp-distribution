package com.gonlg.erp_distribution.presenter.sale

import com.gonlg.erp_distribution.data.request.DistributionIdRequest
import com.gonlg.erp_distribution.data.response.SaleDetailResponse
import com.gonlg.erp_distribution.feature.sale.GetSaleDetailUseCase
import com.gonlg.erp_distribution.presenter.base.BasePresenter
import com.gonlg.erp_distribution.utils.Methods
import io.reactivex.observers.DisposableObserver
import java.io.Serializable
import java.net.SocketTimeoutException

class SaleDetailPresenter (
    private var getSaleDetailUseCase: GetSaleDetailUseCase,
    private var methods: Methods
): BasePresenter<SaleDetailPresenter.View>() {

    fun getSaleDetailUseCase(
        request: DistributionIdRequest,
        listener: (Int, SaleDetailResponse) -> Unit
    ) {
        if (!methods.isInternetConnected()) {
            view?.customWifi()
            return
        }
        view?.showLoading()
        getSaleDetailUseCase.request = request
        getSaleDetailUseCase.execute(object : DisposableObserver<SaleDetailResponse>() {
            override fun onComplete() {
                view?.hideLoading()
            }

            override fun onNext(t: SaleDetailResponse) {
                view?.hideLoading()
                listener(200, t)
            }

            override fun onError(e: Throwable) {
                view?.let { v ->
                    v.hideLoading()
                    when(e) {
                        is SocketTimeoutException -> v.customTimeOut()
                        else -> listener(202, SaleDetailResponse())
                    }
                }
            }
        })
    }

    interface View : BasePresenter.View {
        fun saleDetailSuccessPresenter(status: Int, vararg args: Serializable)
    }
}