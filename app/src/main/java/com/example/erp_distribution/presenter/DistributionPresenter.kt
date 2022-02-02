package com.example.erp_distribution.presenter

import com.example.erp_distribution.data.request.FilterDistributionRequest
import com.example.erp_distribution.data.response.*
import com.example.erp_distribution.feature.distribution.*
import com.example.erp_distribution.presenter.base.BasePresenter
import com.example.erp_distribution.utils.Methods
import io.reactivex.observers.DisposableObserver
import org.json.JSONObject
import retrofit2.HttpException
import java.io.Serializable
import java.net.SocketTimeoutException

class DistributionPresenter (
    private var getListDistributionUseCase: GetListDistributionUseCase,
    private var getListAroundUseCase: GetListAroundUseCase,
    private var getListTowerUseCase: GetListTowerUseCase,
    private var getListLevelUseCase: GetListLevelUseCase,
    private var getListProjectUseCase: GetListProjectUseCase,

    private var methods: Methods
//    private var methods: Methods,
    
): BasePresenter<DistributionPresenter.View>() {

    fun getListAroundUseCase(
        listener: (Int, List<AroundResponse>) -> Unit
    ) {
        if (!methods.isInternetConnected()) {
            view?.customWifi()
            return
        }
        view?.showLoading()

        getListAroundUseCase.execute(object : DisposableObserver<List<AroundResponse>>() {
            override fun onComplete() {
                view?.hideLoading()
            }

            override fun onNext(t: List<AroundResponse>) {
                view?.hideLoading()
                val list: ArrayList<AroundResponse> = arrayListOf()
                list.addAll(t)
                listener(200, list)
            }

            override fun onError(e: Throwable) {
                view?.let { v ->
                    v.hideLoading()
                    when(e) {
                        is SocketTimeoutException -> v.customTimeOut()
                        else -> listener(202, arrayListOf())
                    }
                }
            }
        })
    }

    fun getListTowerUseCase(
        listener: (Int, List<TowerResponse>) -> Unit
    ) {
//        if (!methods.isInternetConnected()) {
//            view?.customWifi()
//            return
//        }
//        view?.showLoading()

        getListTowerUseCase.execute(object : DisposableObserver<List<TowerResponse>>() {
            override fun onComplete() {
                view?.hideLoading()
            }

            override fun onNext(t: List<TowerResponse>) {
                view?.hideLoading()
                val list: ArrayList<TowerResponse> = arrayListOf()
                list.addAll(t)
                listener(200, list)
            }

            override fun onError(e: Throwable) {
                view?.let { v ->
                    v.hideLoading()
                    when(e) {
                        is SocketTimeoutException -> v.customTimeOut()
                        else -> listener(202, arrayListOf())
                    }
                }
            }
        })
    }

    fun getListLevelUseCase(
        listener: (Int, List<LevelResponse>) -> Unit
    ) {
//        if (!methods.isInternetConnected()) {
//            view?.customWifi()
//            return
//        }
//        view?.showLoading()

        getListLevelUseCase.execute(object : DisposableObserver<List<LevelResponse>>() {
            override fun onComplete() {
                view?.hideLoading()
            }

            override fun onNext(t: List<LevelResponse>) {
                view?.hideLoading()
                val list: ArrayList<LevelResponse> = arrayListOf()
                list.addAll(t)
                listener(200, list)
            }

            override fun onError(e: Throwable) {
                view?.let { v ->
                    v.hideLoading()
                    when(e) {
                        is SocketTimeoutException -> v.customTimeOut()
                        else -> listener(202, arrayListOf())
                    }
                }
            }
        })
    }

    fun getListProjectUseCase(
        listener: (Int, List<ProjectResponse>) -> Unit
    ) {
//        if (!methods.isInternetConnected()) {
//            view?.customWifi()
//            return
//        }
//        view?.showLoading()

        getListProjectUseCase.execute(object : DisposableObserver<List<ProjectResponse>>() {
            override fun onComplete() {
                view?.hideLoading()
            }

            override fun onNext(t: List<ProjectResponse>) {
                view?.hideLoading()
                val list: ArrayList<ProjectResponse> = arrayListOf()
                list.addAll(t)
                listener(200, list)
            }

            override fun onError(e: Throwable) {
                view?.let { v ->
                    v.hideLoading()
                    when(e) {
                        is SocketTimeoutException -> v.customTimeOut()
                        else -> listener(202, arrayListOf())
                    }
                }
            }
        })
    }

    fun getListDistributionUseCase(request: FilterDistributionRequest) {

        if (!methods.isInternetConnected()) {
            view?.customWifi()
            return
        }
        view?.showLoading()

        getListDistributionUseCase.request = request

        getListDistributionUseCase.execute(object : DisposableObserver<DistributionResponse>() {
            override fun onComplete() {
                view?.let { v ->
                    v.hideLoading()
                }
            }
            override fun onNext(r: DistributionResponse) {
                view?.let { v ->
                    v.hideLoading()
                    v.distributionSuccessPresenter(200, r)
                }
            }

            override fun onError(e: Throwable) {
                view?.let { v ->
                    v.hideLoading()

                    when (e) {
                        is HttpException -> {
                            val msg: String
                            msg = try {
                                val strJson = e.response()?.errorBody()?.string() ?: "{}"
                                val json = JSONObject(strJson)
                                json.getString("mensaje")
                            } catch (e: Exception) {
                                "Información incorrecta"
                            }
                            v.distributionSuccessPresenter(302, msg)
                        }
                        is SocketTimeoutException -> {
                            v.customTimeOut()
                        }
                        else ->{
                            v.distributionSuccessPresenter(302, "error")
                        }
                    }

                }
            }

        })
    }


    interface View : BasePresenter.View {
        fun distributionSuccessPresenter(status: Int, vararg args: Serializable)
    }
}