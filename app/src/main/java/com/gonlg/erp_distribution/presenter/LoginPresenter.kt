package com.gonlg.erp_distribution.presenter
import com.gonlg.erp_distribution.data.request.CodeCompanyRequest
import com.gonlg.erp_distribution.data.request.DistributionIdRequest
import com.gonlg.erp_distribution.data.request.LoginRequest
import com.gonlg.erp_distribution.data.response.CodeCompanyResponse
import com.gonlg.erp_distribution.data.response.LoginResponse
import com.gonlg.erp_distribution.data.response.SaleDetailResponse
import com.gonlg.erp_distribution.feature.login.CodeCompanyUseCase
import com.gonlg.erp_distribution.feature.login.SignInUseCase
import com.gonlg.erp_distribution.presenter.base.BasePresenter
import com.gonlg.erp_distribution.utils.Methods
import io.reactivex.observers.DisposableObserver
import org.json.JSONObject
import retrofit2.HttpException
import java.io.Serializable
import java.net.SocketTimeoutException

class LoginPresenter (
    private var signInUseCase: SignInUseCase,
    private var codeCompanyUseCase: CodeCompanyUseCase,
    private var methods: Methods
): BasePresenter<LoginPresenter.View>() {

    fun codeCompanyUseCase(
        request: CodeCompanyRequest,
        listener: (Int, CodeCompanyResponse) -> Unit
    ) {
        if (!methods.isInternetConnected()) {
            view?.customWifi()
            return
        }
        view?.showLoading()
        codeCompanyUseCase.request = request
        codeCompanyUseCase.execute(object : DisposableObserver<CodeCompanyResponse>() {
            override fun onComplete() {
                view?.hideLoading()
            }

            override fun onNext(t: CodeCompanyResponse) {
                view?.hideLoading()
                listener(200, t)
            }

            override fun onError(e: Throwable) {
                view?.let { v ->
                    v.hideLoading()
                    when(e) {
                        is SocketTimeoutException -> v.customTimeOut()
                        else -> listener(500, CodeCompanyResponse())
                    }
                }
            }
        })
    }


    fun signInUser(request: LoginRequest) {
        if (!methods.isInternetConnected()) {
            view?.customWifi()
            return
        }
        view?.showLoading()

        signInUseCase.request = request
        signInUseCase.execute(object : DisposableObserver<LoginResponse>() {
            override fun onComplete() {
                view?.let { v ->
                    v.hideLoading()
                }
            }

            override fun onNext(r: LoginResponse) {
                view?.let { v ->
                    v.hideLoading()
                    v.loginSuccessPresenter(200, r)
                }
            }

            override fun onError(e: Throwable) {
                view?.let { v ->
                    v.hideLoading()
                    when (e) {
                        is HttpException -> {
                            v.loginSuccessPresenter(302, LoginResponse().apply {
                                this.success=false
                                this.message = "Incorrecto email o contraseña"
                            })
                        }
                        is SocketTimeoutException -> {
                            v.customTimeOut()
                        }
                        else ->{
                            v.loginSuccessPresenter(302,  LoginResponse().apply {
                                this.success=false
                                this.message = "Error en el servicio"
                            })
                        }
                    }
                }
            }
        })
    }

    interface View : BasePresenter.View {
        fun loginSuccessPresenter(status: Int, vararg args: Serializable)
    }
}