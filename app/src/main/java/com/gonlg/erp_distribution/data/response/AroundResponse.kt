package com.gonlg.erp_distribution.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AroundResponse(
    @SerializedName("active")
    var active: String="",
    @SerializedName("around_name")
    var aroundName: String="",
    @SerializedName("id")
    var id: Int=0
) : Serializable{
    override fun toString(): String {
        return aroundName
    }
}