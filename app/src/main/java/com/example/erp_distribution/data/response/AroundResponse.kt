package com.example.erp_distribution.data.response


import com.google.gson.annotations.SerializedName

data class AroundResponse(
    @SerializedName("active")
    var active: String,
    @SerializedName("around_name")
    var aroundName: String,
    @SerializedName("id")
    var id: Int
)