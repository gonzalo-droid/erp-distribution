package com.solti.inmobiliaria.data.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginRequest (
    @SerializedName("code") var code: String = "",
    @SerializedName("email") var email: String = "",
    @SerializedName("password") var password: String = "",
) : Serializable