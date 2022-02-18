package com.solti.solti_inmobiliaria.data.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DistributionIdRequest (
    @SerializedName("distribution_id") var distributionId: Int = 0,
) : Serializable