package com.example.erp_distribution.presenter

import android.util.Log
import com.example.erp_distribution.data.request.FilterDistributionRequest
import com.example.erp_distribution.data.response.DistributionResponse
import com.example.erp_distribution.feature.distribution.*
import com.example.erp_distribution.presenter.base.BasePresenter
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
//    private var methods: Methods,
    
): BasePresenter<DistributionPresenter.View>() {

    fun getListAroundUseCase() {

    }

    fun getListTowerUseCase() {

    }

    fun getListLevelUseCase() {

    }

    fun getListProjectUseCase() {

    }

    fun getListDistributionUseCase(request: FilterDistributionRequest) {

//        if (!methods.isInternetConnected()) {
//            view?.customWifi()
//            return
//        }
//        view?.showLoading()

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