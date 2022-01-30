package com.example.erp_distribution.data.response


import com.google.gson.annotations.SerializedName

data class ProjectResponse(
    @SerializedName("active")
    var active: String,
    @SerializedName("dep_name")
    var depName: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("dis_name")
    var disName: String,
    @SerializedName("end_date")
    var endDate: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("pro_name")
    var proName: String,
    @SerializedName("project_name")
    var projectName: String,
    @SerializedName("start_date")
    var startDate: String,
    @SerializedName("status")
    var status: String
)