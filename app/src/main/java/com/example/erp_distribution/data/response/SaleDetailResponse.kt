package com.example.erp_distribution.data.response


import com.google.gson.annotations.SerializedName

data class SaleDetailResponse(
    @SerializedName("array")
    var array: Array? = Array(),
    @SerializedName("collection")
    var collection: Collection? = Collection()
)