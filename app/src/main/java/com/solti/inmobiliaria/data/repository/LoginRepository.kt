package com.solti.inmobiliaria.data.repository

import com.solti.inmobiliaria.data.Prefs
import com.solti.inmobiliaria.data.request.CodeCompanyRequest
import com.solti.inmobiliaria.data.request.LoginRequest
import com.solti.inmobiliaria.data.response.CodeCompanyResponse
import com.solti.inmobiliaria.data.response.LoginResponse
import com.solti.inmobiliaria.data.retrofit.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginRepository(var apiService: ApiService, var prefs: Prefs) {

    fun signIn(request: LoginRequest): Observable<LoginResponse> {
        val url = URL_LOGIN
        val headers = mapOf(
            "Content-Type" to "application/json",
        )

        return apiService.signIn(url, headers, request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun codeCompany(request: CodeCompanyRequest): Observable<CodeCompanyResponse> {
        val url = URL_CODE_COMPANY
        val headers = mapOf(
            "Content-Type" to "application/json",
        )

        return apiService.codeCompany(url, headers, request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    companion object {

        val URL_LOGIN = "https://solticrm.com/api/erp-company-code"

        val URL_CODE_COMPANY = "https://solticrm.com/api/erp-company-code"



    }
}