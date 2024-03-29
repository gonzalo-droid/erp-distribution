package com.solti.inmobiliaria.utils

import com.solti.inmobiliaria.data.response.*
import io.paperdb.BuildConfig
import io.paperdb.Paper

object PapersManager {

    var responseDistributions: DistributionResponse
        set(value) {
            Paper.book(BuildConfig.FLAVOR).write("responseDistributions", value)
        }
        get() {
            return Paper.book(BuildConfig.FLAVOR).read("responseDistributions", DistributionResponse())
        }

    var responseSaleDetail: SaleDetailResponse
        set(value) {
            Paper.book(BuildConfig.FLAVOR).write("responseSaleDetail", value)
        }
        get() {
            return Paper.book(BuildConfig.FLAVOR).read("responseSaleDetail", SaleDetailResponse())
        }

    var responseCodeCompany: CodeCompanyResponse
        set(value) {
            Paper.book(BuildConfig.FLAVOR).write("responseCodeCompany", value)
        }
        get() {
            return Paper.book(BuildConfig.FLAVOR).read("responseCodeCompany", CodeCompanyResponse())
        }


    var getLevels: ArrayList<LevelResponse>
        set(value) {
            Paper.book(BuildConfig.FLAVOR).write("getLevels", value)
        }
        get() {
            return Paper.book(BuildConfig.FLAVOR).read("getLevels", arrayListOf())
        }

    var getProjects: ArrayList<ProjectResponse>
        set(value) {
            Paper.book(BuildConfig.FLAVOR).write("getProjects", value)
        }
        get() {
            return Paper.book(BuildConfig.FLAVOR).read("getProjects", arrayListOf())
        }

    var getTowers: ArrayList<TowerResponse>
        set(value) {
            Paper.book(BuildConfig.FLAVOR).write("getTowers", value)
        }
        get() {
            return Paper.book(BuildConfig.FLAVOR).read("getTowers", arrayListOf())
        }

    var getArounds: ArrayList<AroundResponse>
        set(value) {
            Paper.book(BuildConfig.FLAVOR).write("getArounds", value)
        }
        get() {
            return Paper.book(BuildConfig.FLAVOR).read("getArounds", arrayListOf())
        }

    var getStatus: ArrayList<StatusResponse>
        set(value) {
            Paper.book(BuildConfig.FLAVOR).write("getStatus", value)
        }
        get() {
            return Paper.book(BuildConfig.FLAVOR).read("getStatus", arrayListOf())
        }
    var login: LoginResponse
        set(value) {
            Paper.book(BuildConfig.FLAVOR).write("login", value)
        }
        get() {
            return Paper.book(BuildConfig.FLAVOR).read("login", LoginResponse())
        }
}