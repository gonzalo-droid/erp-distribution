package com.solti.inmobiliaria.data.repository

import com.solti.inmobiliaria.data.Prefs
import com.solti.inmobiliaria.data.request.FilterDistributionRequest
import com.solti.inmobiliaria.data.response.*
import com.solti.inmobiliaria.data.retrofit.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DistributionRepository(var apiService: ApiService, var prefs: Prefs) {

    fun getListDistribution(request: FilterDistributionRequest): Observable<DistributionResponse> {
        val url = url(prefs.url.toString(),URL_LIST_DISTRIBUTION )
        val headers = mapOf(
            "Content-Type" to "application/json",
            "Authorization" to token(prefs),
        )

        return apiService.getListDistribution(url, headers, request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getTowers(): Observable<List<TowerResponse>> {
        val url = url(prefs.url.toString(),URL_TOWER )
        val headers = mapOf(
            "Content-Type" to "application/json",
            "Authorization" to token(prefs),
        )

        return apiService.getTowers(url, headers)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getLevels(): Observable<List<LevelResponse>> {
        val url = url(prefs.url.toString(),URL_LEVEL )
        val headers = mapOf(
            "Content-Type" to "application/json",
            "Authorization" to token(prefs),
        )

        return apiService.getLevels(url, headers)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getArounds(): Observable<List<AroundResponse>> {
        val url = url(prefs.url.toString(),URL_AROUND )
        val headers = mapOf(
            "Content-Type" to "application/json",
            "Authorization" to token(prefs),
        )

        return apiService.getArounds(url, headers)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    }

    fun getProjects(): Observable<List<ProjectResponse>> {
        val url = url(prefs.url.toString(),URL_PROJECT )
        val headers = mapOf(
            "Content-Type" to "application/json",
            "Authorization" to token(prefs),
            "Connection" to "close"
        )

        return apiService.getProjects(url, headers)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {

        fun token(prefs: Prefs) = prefs.token.toString()
        fun url(url: String, endpoint: String) = "${url}${endpoint}"

        val URL_LIST_DISTRIBUTION =  "/report-distribution/filter"
        val URL_LEVEL =  "/all_levels"
        val URL_AROUND =  "/all_arounds"
        val URL_PROJECT =  "/all_projects"
        val URL_TOWER =  "/all_towers"




    }
}