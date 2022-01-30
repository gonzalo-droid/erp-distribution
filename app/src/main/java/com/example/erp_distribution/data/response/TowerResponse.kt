package com.example.erp_distribution.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TowerResponse(
    @SerializedName("active")
    var active: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("tower_name")
    var towerName: String =""
) : Serializable