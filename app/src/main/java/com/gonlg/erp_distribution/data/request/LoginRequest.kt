package com.gonlg.erp_distribution.data.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginRequest (
    @SerializedName("email") var email: String = "",
    @SerializedName("password") var password: String = "",
) : Serializable