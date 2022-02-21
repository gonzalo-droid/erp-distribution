package com.solti.solti_inmobiliaria.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Distributions(
    @SerializedName("around_name")
    var aroundName: String = "",
    @SerializedName("code")
    var code: String = "",
    @SerializedName("distribution_name_level_around_number")
    var distributionNameLevelAroundNumber: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("level_around_name")
    var levelAroundName: String = "",
    @SerializedName("level_name")
    var levelName: String = "",
    @SerializedName("project_name")
    var projectName: String = "",
    @SerializedName("status")
    var status: String = "",
    @SerializedName("status_distribution_sale")
    var statusDistributionSale: String = "",
    @SerializedName("payment_status")
    var paymentStatus: String = "",
    @SerializedName("tower_name")
    var towerName: String = ""
) : Serializable