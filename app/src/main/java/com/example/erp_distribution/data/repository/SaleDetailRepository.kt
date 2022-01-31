package com.example.erp_distribution.data.repository

import android.util.Log
import com.example.erp_distribution.data.request.DistributionIdRequest
import com.example.erp_distribution.data.request.FilterDistributionRequest
import com.example.erp_distribution.data.response.*
import com.example.erp_distribution.data.retrofit.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SaleDetailRepository (var apiService: ApiService) {

    fun getSaleDetail(request: DistributionIdRequest): Observable<SaleDetailResponse> {
        val url = "${URL_SALE}?distribution_id=${request.distributionId}"
        val headers = mapOf(
            "Content-Type" to "application/json",
        )
        Log.d("Repo->",request.toString())
        return apiService.getDetailSale(url, headers)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {

        val URL_SALE =  "https://sistemasolti-murano.com/api/sale-first"

    }
}