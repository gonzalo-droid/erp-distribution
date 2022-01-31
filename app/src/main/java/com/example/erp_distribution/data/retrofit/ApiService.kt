package com.example.erp_distribution.data.retrofit

import com.example.erp_distribution.data.request.DistributionIdRequest
import com.example.erp_distribution.data.request.FilterDistributionRequest
import com.example.erp_distribution.data.response.*
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    @GET
    fun getTowers(
        @Url url: String,
        @HeaderMap headers: Map<String, String>
    ): Observable<List<TowerResponse>>

    @GET
    fun getLevels(
        @Url url: String,
        @HeaderMap headers: Map<String, String>
    ): Observable<List<LevelResponse>>

    @GET
    fun getArounds(
        @Url url: String,
        @HeaderMap headers: Map<String, String>
    ): Observable<List<AroundResponse>>

    @GET
    fun getProjects(
        @Url url: String,
        @HeaderMap headers: Map<String, String>
    ): Observable<List<ProjectResponse>>

    @GET
    fun getDetailSale(
        @Url url: String,
        @HeaderMap headers: Map<String, String>
    ): Observable<SaleDetailResponse>

    @POST
    fun getListDistribution(
        @Url url: String,
        @HeaderMap headers: Map<String, String>,
        @Body request: FilterDistributionRequest
    ): Observable<DistributionResponse>



}