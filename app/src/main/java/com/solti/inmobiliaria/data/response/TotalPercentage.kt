package com.solti.inmobiliaria.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TotalPercentage(
    @SerializedName("active_percentage")
    var activePercentage: String = "",
    @SerializedName("free_percentage")
    var freePercentage: String = "",
    @SerializedName("expired_percentage")
    var expiredPercentage: String = "" ,
    @SerializedName("incomplete_percentage")
    var incompletePercentage: String = "",
    @SerializedName("no_debt_percentage")
    var noDebtPercentage: String = ""
) : Serializable