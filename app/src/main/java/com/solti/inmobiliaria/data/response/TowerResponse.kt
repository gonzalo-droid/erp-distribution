package com.solti.inmobiliaria.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TowerResponse(
    @SerializedName("active")
    var active: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("tower_name")
    var towerName: String =""
) : Serializable{
    override fun toString(): String {
        return towerName
    }
}