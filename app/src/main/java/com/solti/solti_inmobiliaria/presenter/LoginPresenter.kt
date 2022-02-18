package com.solti.solti_inmobiliaria.presenter
import com.solti.solti_inmobiliaria.data.request.CodeCompanyRequest
import com.solti.solti_inmobiliaria.data.request.LoginRequest
import com.solti.solti_inmobiliaria.data.response.CodeCompanyResponse
import com.solti.solti_inmobiliaria.data.response.LoginResponse
import com.solti.solti_inmobiliaria.feature.login.CodeCompanyUseCase
import com.solti.solti_inmobiliaria.feature.login.SignInUseCase
import com.solti.solti_inmobiliaria.presenter.base.BasePresenter
import com.solti.solti_inmobiliaria.utils.Methods
import io.reactivex.observers.DisposableObserver
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
                            v.loginSuccessPresenter(302, LoginResponse())
                        }
                        is SocketTimeoutException -> {
                            v.customTimeOut()
                        }
                        else ->{
                            v.loginSuccessPresenter(302,  LoginResponse())
                        }
                    }
                }
            }
        })
    }

    fun sessionPrefs(){

    }
    interface View : BasePresenter.View {
        fun loginSuccessPresenter(status: Int, vararg args: Serializable)
    }
}