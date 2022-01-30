package com.example.erp_distribution.utils

import com.example.erp_distribution.data.response.DistributionResponse
import io.paperdb.BuildConfig
import io.paperdb.Paper

object PapersManager {

    var responseDistributions: DistributionResponse
        set(value) {
            Paper.book(BuildConfig.FLAVOR).write("responseDistributions", value)
        }
        get() {
            return Paper.book(BuildConfig.FLAVOR).read("responseDistributions", DistributionResponse())
        }

}