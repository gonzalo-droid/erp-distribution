package com.solti.solti_inmobiliaria.data.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CodeCompanyRequest (
    @SerializedName("code") var code: String = "",
) : Serializable