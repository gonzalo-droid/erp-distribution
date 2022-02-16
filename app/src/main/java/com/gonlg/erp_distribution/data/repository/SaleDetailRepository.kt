package com.gonlg.erp_distribution.data.repository

import com.gonlg.erp_distribution.data.Prefs
import com.gonlg.erp_distribution.data.request.DistributionIdRequest
import com.gonlg.erp_distribution.data.response.SaleDetailResponse
import com.gonlg.erp_distribution.data.retrofit.ApiService
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