package com.example.erp_distribution.ui.activity.distribution

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.erp_distribution.R
import com.example.erp_distribution.data.request.FilterDistributionRequest
import com.example.erp_distribution.data.response.DistributionResponse
import com.example.erp_distribution.data.response.Distributions
import com.example.erp_distribution.databinding.ActivityDistributionMapBinding
import com.example.erp_distribution.presenter.DistributionPresenter
import com.example.erp_distribution.ui.adapter.DistributionAdapter
import com.example.erp_distribution.ui.base.ErpBaseActivity
import com.example.erp_distribution.utils.PapersManager
import com.google.android.material.snackbar.Snackbar
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


        setAdapter()
    }

    private fun setAdapter() {
        @Suppress("SENSELESS_COMPARISON")
        if (distributionAdapter == null) {
            distributionAdapter = DistributionAdapter { status, distribution  ->
                when (status) {
                    0 -> {
                        dialogDistribution(distribution)
                    }
                    else -> {
                        Toast.makeText(this, "Sin información", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            binding.recycler.removeAllViews()
            binding.recycler.removeAllViewsInLayout()
            binding.recycler.adapter = distributionAdapter
            distributionAdapter!!.data = PapersManager.responseDistributions.listDistributions
        } else {
            distributionAdapter!!.data = PapersManager.responseDistributions.listDistributions
            distributionAdapter!!.notifyDataSetChanged()
        }
    }

    fun dialogDistribution(distribution: Distributions) {

        val dialog = Dialog(getContext())

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_distribution)

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.window?.attributes?.windowAnimations = R.style.AppTheme_Slide
        dialog.setCancelable(true)
        dialog.show()
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
                val response = args[0] as DistributionResponse
                Log.d("Test", response.toString())
//                if(response.status){
//
//                }
                PapersManager.responseDistributions = response
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