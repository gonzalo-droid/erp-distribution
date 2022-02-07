package com.gonlg.erp_distribution.data.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CodeCompanyRequest (
    @SerializedName("code") var cpde: String = "",
) : Serializable