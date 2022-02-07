package com.gonlg.erp_distribution.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class LevelResponse(
    @SerializedName("active")
    var active: String="",
    @SerializedName("id")
    var id: Int=0,
    @SerializedName("level_name")
    var levelName: String=""
) : Serializable{
    override fun toString(): String {
        return levelName
    }
}


