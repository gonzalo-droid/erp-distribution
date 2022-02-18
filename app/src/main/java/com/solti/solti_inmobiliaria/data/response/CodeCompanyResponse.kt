package com.solti.solti_inmobiliaria.data.response

import com.solti.solti_inmobiliaria.data.model.CodeCompany
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CodeCompanyResponse (
    @SerializedName("api_code")
    var apiCode: Int = 0,
    @SerializedName("api_description")
    var apiDescription: String = "",
    @SerializedName("api_message")
    var apiMessage: String = "",
    @SerializedName("api_status")
    var apiStatus: String = "",
    @SerializedName("data")
    var codeCompany: CodeCompany = CodeCompany(),
    @SerializedName("message")
    var message: String = "",
    @SerializedName("success")
    var success: String = ""

) : Serializable