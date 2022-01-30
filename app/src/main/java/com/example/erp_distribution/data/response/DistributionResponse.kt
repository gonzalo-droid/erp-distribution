package com.example.erp_distribution.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ListDistributionResponse(
    @SerializedName("list_distributions") var listDistributions: List<Distributions> = arrayListOf(),
    @SerializedName("status") var status: String = "",
    @SerializedName("total_distributions") var totalDistributions: TotalDistributions,
    @SerializedName("total_percentage") var totalPercentage: TotalPercentage
): Serializable