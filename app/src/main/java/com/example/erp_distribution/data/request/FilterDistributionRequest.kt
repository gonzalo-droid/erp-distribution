package com.example.erp_distribution.data.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FilterDistributionRequest (
    @SerializedName("project_id") var projectId: String = "",
    @SerializedName("around_select") var aroundSelect: String = "",
    @SerializedName("level_select") var levelSelect: String = "",
    @SerializedName("stage_select") var stageSelect: String = "",
    @SerializedName("status") var status: String = ""
) : Serializable