package com.solti.solti_inmobiliaria.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProjectResponse(
    @SerializedName("active")
    var active: String="",
    @SerializedName("dep_name")
    var depName: String="",
    @SerializedName("description")
    var description: String="",
    @SerializedName("dis_name")
    var disName: String="",
    @SerializedName("end_date")
    var endDate: String="",
    @SerializedName("id")
    var id: Int=0,
    @SerializedName("pro_name")
    var proName: String="",
    @SerializedName("project_name")
    var projectName: String="",
    @SerializedName("start_date")
    var startDate: String="",
    @SerializedName("status")
    var status: String=""
) : Serializable {
    override fun toString(): String {
        return projectName
    }
}