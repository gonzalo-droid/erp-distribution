package com.gonlg.erp_distribution.data.response

import com.gonlg.erp_distribution.data.model.CodeCompany
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse (
    @SerializedName("success")
    var success: Boolean = false,
    @SerializedName("message")
    var message: String = "",
    @SerializedName("token")
    var token: String = "",
) : Serializable