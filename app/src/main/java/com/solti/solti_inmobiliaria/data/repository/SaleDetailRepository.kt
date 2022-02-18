package com.solti.solti_inmobiliaria.data.repository

import com.solti.solti_inmobiliaria.data.Prefs
import com.solti.solti_inmobiliaria.data.request.DistributionIdRequest
import com.solti.solti_inmobiliaria.data.response.SaleDetailResponse
import com.solti.solti_inmobiliaria.data.retrofit.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SaleDetailRepository (var apiService: ApiService, var prefs: Prefs) {

    fun getSaleDetail(request: DistributionIdRequest): Observable<SaleDetailResponse> {
        val url = url(prefs.url.toString(),request)
        val headers = mapOf(
            "Content-Type" to "application/json",
            "Authorization" to token(prefs),
            "Cache-Control" to "no-cache"
        )
        return apiService.getDetailSale(url, headers)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {
        fun token(prefs: Prefs) = prefs.token.toString()

        fun url(url: String,request: DistributionIdRequest) =
            "${url}/sale-first?distribution_id=${request.distributionId}"

    }
}