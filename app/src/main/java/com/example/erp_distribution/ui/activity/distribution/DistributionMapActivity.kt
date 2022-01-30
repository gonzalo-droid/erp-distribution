package com.example.erp_distribution.ui.activity.distribution

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import com.example.erp_distribution.R
import com.example.erp_distribution.data.request.FilterDistributionRequest
import com.example.erp_distribution.data.response.ListDistributionResponse
import com.example.erp_distribution.databinding.ActivityDistributionMapBinding
import com.example.erp_distribution.databinding.ActivityMainBinding
import com.example.erp_distribution.presenter.DistributionPresenter
import com.example.erp_distribution.ui.adapter.DistributionAdapter
import com.example.erp_distribution.ui.base.ErpBaseActivity
import java.io.Serializable
import javax.inject.Inject

class DistributionMapActivity : ErpBaseActivity(), DistributionPresenter.View {

    @Inject
    lateinit var distributionPresenter: DistributionPresenter

    private var distributionAdapter: DistributionAdapter? = null
    private lateinit var binding: ActivityDistributionMapBinding

    override fun getView(): Int = R.layout.activity_distribution_map

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDistributionMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        component.inject(this)
        distributionPresenter.attachView(this)


        distributionPresenter.getListDistributionUseCase(FilterDistributionRequest().apply {
            this.projectId = "1"
            this.aroundSelect = ""
            this.levelSelect = ""
            this.projectSelect = ""
            this.stageSelect = ""
            this.status = ""
        })

    }

    private fun setAdapter() {
        @Suppress("SENSELESS_COMPARISON")
        if (distributionAdapter == null) {
//            distributionAdapter = DistributionAdapter { id, status  ->
//                when (status) {
//                    0 -> distributionPresenter.checkPriceComplete(
//                        product.sku,
//                        quantity.toLong(),
//                        false )
//                    1 -> distributionPresenter.checkPriceComplete(
//                        product.sku,
//                        quantity.toLong(),
//                        false )
//                    2 -> {
//                        showDialogDiscount(product, "ripley")
//                    }
//                }
//            }
            binding.recycler.removeAllViews()
            binding.recycler.removeAllViewsInLayout()
            binding.recycler.adapter = distributionAdapter
//            distributionAdapter!!.data = PapersManager.shoppingCart.products
        } else {
//            distributionAdapter!!.data = PapersManager.shoppingCart.products
//            distributionAdapter!!.notifyDataSetChanged()
        }
    }


    override fun onResume() {
        distributionPresenter.attachView(this)
        super.onResume()
    }

    @SuppressLint("MissingPermission")
    override fun onPause() {
        distributionPresenter.detachView()
        super.onPause()
    }


    override fun distributionSuccessPresenter(status: Int, vararg args: Serializable) {
        when (status) {
            200 -> {
//                val shopping = args[0] as CheckPricesResponse
//                val positions: MutableMap<Int, Int> = mutableMapOf()
//                val ws = PapersManager.warranties
            }
            300 -> {

            }
            else -> {
                Log.d("Error", "e")
            }
        }
    }

    override fun customWifi() {
        TODO("Not yet implemented")
    }


    override fun customTimeOut() {
        TODO("Not yet implemented")
    }

}