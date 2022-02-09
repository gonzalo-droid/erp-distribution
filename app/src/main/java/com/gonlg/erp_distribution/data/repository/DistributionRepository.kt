package com.gonlg.erp_distribution.data.repository

import com.gonlg.erp_distribution.data.request.FilterDistributionRequest
import com.gonlg.erp_distribution.data.response.*
import com.gonlg.erp_distribution.data.retrofit.ApiService
import com.gonlg.erp_distribution.utils.Methods
import com.gonlg.erp_distribution.utils.PapersManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DistributionRepository(var apiService: ApiService) {

    fun getListDistribution(request: FilterDistributionRequest): Observable<DistributionResponse> {
        val url = URL_LIST_DISTRIBUTION
        val headers = mapOf(
            "Content-Type" to "application/json",
            "Authorization" to TOKEN
        )

        return apiService.getListDistribution(url, headers, request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getTowers(): Observable<List<TowerResponse>> {
        val url = URL_TOWER
        val headers = mapOf(
            "Content-Type" to "application/json",
            "Authorization" to TOKEN
        )

        return apiService.getTowers(url, headers)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getLevels(): Observable<List<LevelResponse>> {
        val url = URL_LEVEL
        val headers = mapOf(
            "Content-Type" to "application/json",
            "Authorization" to TOKEN
        )

        return apiService.getLevels(url, headers)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getArounds(): Observable<List<AroundResponse>> {
        val url = URL_AROUND
        val headers = mapOf(
            "Content-Type" to "application/json",
            "Authorization" to TOKEN
        )

        return apiService.getArounds(url, headers)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getProjects(): Observable<List<ProjectResponse>> {
        val url = URL_PROJECT
        val headers = mapOf(
            "Content-Type" to "application/json",
            "Authorization" to TOKEN
        )

        return apiService.getProjects(url, headers)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {

        val URL_LIST_DISTRIBUTION =  "${PapersManager.urlBase}/report-distribution/filter" 
        
        val URL_LEVEL =  "${PapersManager.urlBase}/all_levels"

        val URL_AROUND =  "${PapersManager.urlBase}/all_arounds"

        val URL_PROJECT =  "${PapersManager.urlBase}/all_projects"

        val URL_TOWER =  "${PapersManager.urlBase}/all_towers"

        val TOKEN =  PapersManager.token


    }
}