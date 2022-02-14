package com.gonlg.erp_distribution.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse (
    var apiCode: Int = 0,
    @SerializedName("api_description")
    var apiDescription: String = "",
    @SerializedName("api_message")
    var apiMessage: String = "",
    @SerializedName("api_status")
    var apiStatus: String = "",
    @SerializedName("data_login")
    var dataLogin: DataLogin = DataLogin(),
    @SerializedName("data_company")
    var dataCompany: DataCompany = DataCompany(),
    @SerializedName("message")
    var message: String = "",
    @SerializedName("success")
    var success: String = ""
) : Serializable {
    data class DataLogin(
        @SerializedName("success")
        var success: Boolean = false,
        @SerializedName("token")
        var token: String = ""
    ):Serializable


    data class DataCompany(
        @SerializedName("code")
        var code: String = "",
        @SerializedName("name")
        var name: String = "",
        @SerializedName("url")
        var url: String = "",
    ):Serializable
}


