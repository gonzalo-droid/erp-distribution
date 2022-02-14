package com.gonlg.erp_distribution.data.repository

import android.util.Log
import com.gonlg.erp_distribution.data.Prefs
import com.gonlg.erp_distribution.data.request.DistributionIdRequest
import com.gonlg.erp_distribution.data.response.SaleDetailResponse
import com.gonlg.erp_distribution.data.retrofit.ApiService
import com.gonlg.erp_distribution.utils.PapersManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SaleDetailRepository (var apiService: ApiService, var prefs: Prefs) {

    fun getSaleDetail(request: DistributionIdRequest): Observable<SaleDetailResponse> {
        val url = "${URL_SALE}?distribution_id=${request.distributionId}"
        val headers = mapOf(
            "Content-Type" to "application/json",
            "Authorization" to TOKEN,
            "Cache-Control" to "no-cache"
        )
        Log.d("Repo->",request.toString())
        return apiService.getDetailSale(url, headers)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {

        val URL_SALE =  "${PapersManager.login.dataCompany.url}/sale-first"
        val TOKEN = PapersManager.login.dataLogin.token

    }
}