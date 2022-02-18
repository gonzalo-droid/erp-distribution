package com.solti.solti_inmobiliaria.data.model


import com.google.gson.annotations.SerializedName

data class CodeCompany(
    @SerializedName("active")
    var active: String = "",
    @SerializedName("code")
    var code: String = "",
    @SerializedName("created_at")
    var createdAt: String = "",
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("updated_at")
    var updatedAt: String = "",
    @SerializedName("url")
    var url: String = ""
)