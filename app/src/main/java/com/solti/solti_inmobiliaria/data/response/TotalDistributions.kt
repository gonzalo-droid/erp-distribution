package com.solti.solti_inmobiliaria.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TotalDistributions(
    @SerializedName("distribution_active")
    var distributionActive: Int = 0,
    @SerializedName("distribution_free")
    var distributionFree: Int = 0,
    @SerializedName("distribution_expired")
    var distributionExpired: Int = 0,
    @SerializedName("distribution_incomplete")
    var distributionIncomplete: Int = 0,
    @SerializedName("distribution_no_debt")
    var distributionNoDebt: Int = 0
) : Serializable