package com.solti.inmobiliaria.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DistributionResponse(
    @SerializedName("list_distributions") var listDistributions: ArrayList<Distributions> = arrayListOf(),
    @SerializedName("status") var status: String = "",
    @SerializedName("total_distributions") var totalDistributions: TotalDistributions =TotalDistributions(),
    @SerializedName("total_percentage") var totalPercentage: TotalPercentage = TotalPercentage()
): Serializable