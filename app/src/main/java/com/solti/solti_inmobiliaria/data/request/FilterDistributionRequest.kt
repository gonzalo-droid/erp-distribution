package com.solti.solti_inmobiliaria.data.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FilterDistributionRequest (
    @SerializedName("project_id") var projectId: String = "",
    @SerializedName("around_select") var aroundId: String = "",
    @SerializedName("level_select") var levelId: String = "",
    @SerializedName("stage_select") var stageId: String = "",
    @SerializedName("status") var statusId: String = ""
) : Serializable