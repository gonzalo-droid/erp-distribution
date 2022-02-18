package com.solti.solti_inmobiliaria.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TotalPercentage(
    @SerializedName("active_percentage")
    var activePercentage: String = "",
    @SerializedName("free_percentage")
    var freePercentage: String = "",
    @SerializedName("separated_percentage")
    var separatedPercentage: String = "" ,
    @SerializedName("sold_percentage")
    var soldPercentage: String = ""
) : Serializable