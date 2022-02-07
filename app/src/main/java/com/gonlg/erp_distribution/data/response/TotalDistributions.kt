package com.gonlg.erp_distribution.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TotalDistributions(
    @SerializedName("distribution_active")
    var distributionActive: Int = 0,
    @SerializedName("distribution_free")
    var distributionFree: Int = 0,
    @SerializedName("distribution_separated")
    var distributionSeparated: Int = 0,
    @SerializedName("distribution_sold")
    var distributionSold: Int = 0
) : Serializable