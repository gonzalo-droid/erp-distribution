package com.example.erp_distribution.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class StatusResponse (
    @SerializedName("active")
    var active: String="",
    @SerializedName("id")
    var id: Int=0,
    @SerializedName("name")
    var name: String=""
) : Serializable {
    override fun toString(): String {
        return name
    }
}