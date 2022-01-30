package com.example.erp_distribution.data.repository

import com.example.erp_distribution.data.request.FilterDistributionRequest
import com.example.erp_distribution.data.response.ListDistributionResponse
import com.example.erp_distribution.data.retrofit.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DistributionRepository(var apiService: ApiService) {

    fun getListDistribution(request: FilterDistributionRequest): Observable<ListDistributionResponse> {
        val url = URL_LIST_DISTRIBUTION
        val headers = mapOf(
            "Content-Type" to "application/json",
        )

        return apiService.getListDistribution(url, headers, request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {

        val URL_LIST_DISTRIBUTION = "report-distribution/filter"


    }
}