package com.gonlg.erp_distribution.presenter
import com.gonlg.erp_distribution.feature.login.CodeCompanyUseCase
import com.gonlg.erp_distribution.feature.login.SignInUseCase
import com.gonlg.erp_distribution.presenter.base.BasePresenter
import com.gonlg.erp_distribution.utils.Methods
import java.io.Serializable

class LoginPresenter (
    private var signInUseCase: SignInUseCase,
    private var codeCompanyUseCase: CodeCompanyUseCase,
    private var methods: Methods
): BasePresenter<LoginPresenter.View>() {

//    fun signInUseCase(
//        request: DistributionIdRequest,
//    ) {
//        if (!methods.isInternetConnected()) {
//            view?.customWifi()
//            return
//        }
//        view?.showLoading()
//        signInUseCase.request = request
//        signInUseCase.execute(object : DisposableObserver<SaleDetailResponse>() {
//            override fun onComplete() {
//                view?.hideLoading()
//            }
//
//            override fun onNext(t: SaleDetailResponse) {
//                view?.hideLoading()
//                listener(200, t)
//            }
//
//            override fun onError(e: Throwable) {
//                view?.let { v ->
//                    v.hideLoading()
//                    when(e) {
//                        is SocketTimeoutException -> v.customTimeOut()
//                        else -> listener(202, SaleDetailResponse())
//                    }
//                }
//            }
//        })
//    }

    interface View : BasePresenter.View {
        fun loginSuccessPresenter(status: Int, vararg args: Serializable)
    }
}