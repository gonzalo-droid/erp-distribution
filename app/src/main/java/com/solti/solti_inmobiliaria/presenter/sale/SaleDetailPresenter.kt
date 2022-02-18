package com.solti.solti_inmobiliaria.presenter.sale

import com.solti.solti_inmobiliaria.data.request.DistributionIdRequest
import com.solti.solti_inmobiliaria.data.response.SaleDetailResponse
import com.solti.solti_inmobiliaria.feature.sale.GetSaleDetailUseCase
import com.solti.solti_inmobiliaria.presenter.base.BasePresenter
import com.solti.solti_inmobiliaria.utils.Methods
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