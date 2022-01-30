package com.example.erp_distribution.data.retrofit

import com.example.erp_distribution.data.request.FilterDistributionRequest
import com.example.erp_distribution.data.response.ListDistributionResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {

    @POST
    fun getListDistribution(
        @Url url: String,
        @HeaderMap headers: Map<String, String>,
        @Body request: FilterDistributionRequest
    ): Observable<ListDistributionResponse>


}