package com.example.erp_distribution.data.repository

import com.example.erp_distribution.data.request.FilterDistributionRequest
import com.example.erp_distribution.data.response.*
import com.example.erp_distribution.data.retrofit.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DistributionRepository(var apiService: ApiService) {

    fun getListDistribution(request: FilterDistributionRequest): Observable<DistributionResponse> {
        val url = URL_LIST_DISTRIBUTION
        val headers = mapOf(
            "Content-Type" to "application/json",
        )

        return apiService.getListDistribution(url, headers, request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getTowers(): Observable<List<TowerResponse>> {
        val url = URL_TOWER
        val headers = mapOf(
            "Content-Type" to "application/json",
        )

        return apiService.getTowers(url, headers)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getLevels(): Observable<List<LevelResponse>> {
        val url = URL_LEVEL
        val headers = mapOf(
            "Content-Type" to "application/json",
        )

        return apiService.getLevels(url, headers)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getArounds(): Observable<List<AroundResponse>> {
        val url = URL_AROUND
        val headers = mapOf(
            "Content-Type" to "application/json",
        )

        return apiService.getArounds(url, headers)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getProjects(): Observable<List<ProjectResponse>> {
        val url = URL_PROJECT
        val headers = mapOf(
            "Content-Type" to "application/json",
        )

        return apiService.getProjects(url, headers)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {

        val URL_LIST_DISTRIBUTION =  "https://sistemasolti-murano.com/api/report-distribution/filter"

        val URL_LEVEL =  "https://sistemasolti-murano.com/api/all_levels"

        val URL_AROUND =  "https://sistemasolti-murano.com/api/all_arounds"

        val URL_PROJECT =  "https://sistemasolti-murano.com/api/all_projects"

        val URL_TOWER =  "https://sistemasolti-murano.com/api/all_towers"


    }
}