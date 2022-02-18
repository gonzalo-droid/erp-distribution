package com.solti.solti_inmobiliaria.ui.activity.distribution

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.appcompat.widget.*
import com.solti.solti_inmobiliaria.R
import com.solti.solti_inmobiliaria.data.Prefs
import com.solti.solti_inmobiliaria.data.request.DistributionIdRequest
import com.solti.solti_inmobiliaria.data.request.FilterDistributionRequest
import com.solti.solti_inmobiliaria.data.response.*
import com.solti.solti_inmobiliaria.databinding.ActivityDistributionBinding
import com.solti.solti_inmobiliaria.presenter.DistributionPresenter
import com.solti.solti_inmobiliaria.presenter.sale.SaleDetailPresenter
import com.solti.solti_inmobiliaria.ui.activity.login.LoginActivity
import com.solti.solti_inmobiliaria.ui.adapter.DistributionAdapter
import com.solti.solti_inmobiliaria.ui.application.ErpApplication
import com.solti.solti_inmobiliaria.ui.base.ErpBaseActivity
import com.solti.solti_inmobiliaria.utils.PapersManager
import com.solti.solti_inmobiliaria.utils.startActivityTo
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.item_total.view.*
import kotlinx.android.synthetic.main.section_filter_summary.view.*
import timber.log.Timber
import java.io.Serializable
import javax.inject.Inject
import kotlin.math.nextUp
import kotlin.math.roundToInt

class DistributionActivity : ErpBaseActivity(), DistributionPresenter.View, SaleDetailPresenter.View {


    @Inject
    lateinit var distributionPresenter: DistributionPresenter

    @Inject
    lateinit var saleDetailPresenter: SaleDetailPresenter

    @Inject
    lateinit var prefs: Prefs

    private var distributionAdapter: DistributionAdapter? = null
    private lateinit var binding: ActivityDistributionBinding

    /* values of data at filter dialog*/
    private var projectSelect = "1"
    private var levelSelect = ""
    private var towerSelect = ""
    private var aroundSelect = ""
    private var statusSelect = ""

    private var projectSelectName = ""
    private var levelSelectName = ""
    private var towerSelectName = ""
    private var aroundSelectName = ""
    private var statusSelectName = ""

    /* values of index data at filter dialog*/
    private var projectIndex = 0
    private var levelIndex = 0
    private var towerIndex = 0
    private var aroundIndex = 0
    private var statusIndex = 0


    override fun getView(): Int = R.layout.activity_distribution

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDistributionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        component.inject(this)
        distributionPresenter.attachView(this)
        saleDetailPresenter.attachView(this)

        distributionPresenter.getListProjectUseCase(
            listener = { i, list  ->
                when (i) {
                    200 -> {
                        // TODO add all filter
                        PapersManager.getProjects = list as ArrayList<ProjectResponse>
                        getFilterData()
                    }
                    else -> {
                        customDialogExpiredSession(prefs)
                    }
                }
            })


        binding.lnlEmpty.visibility = View.VISIBLE
        binding.tvFailSearch.visibility = View.GONE
        binding.tvStartSearch.visibility = View.VISIBLE
        binding.recycler.visibility = View.GONE
        binding.lnlFilter.visibility = View.GONE
        binding.lnlFilterSummary.visibility = View.GONE

        binding.txtTitle.text = prefs.nameCompany
        binding.fab.setOnClickListener { view ->
            dialogFilter()
        }

        binding.btnProfile.setOnClickListener {
            showBottomSheetDialog()
        }


        if(prefs.session){
            PapersManager.login.dataLogin.token = prefs.token.toString()
            PapersManager.login.dataCompany.name = prefs.nameCompany.toString()
            PapersManager.login.dataCompany.url = prefs.url.toString()
            PapersManager.login.dataCompany.name = prefs.nameCompany.toString()
        }

        setAdapter()
    }

    @SuppressLint("SetTextI18n")
    private fun showBottomSheetDialog() {
        val view = layoutInflater.inflate(R.layout.bottom_option, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val btnCloseSession: LinearLayoutCompat = dialog.findViewById<View>(R.id.btnCloseSession) as LinearLayoutCompat
        btnCloseSession.setOnClickListener {
            customDialogCloseSession(prefs)
            dialog.dismiss()
        }
        dialog.show()
    }



    private fun dialogFilter() {
        val dialog = Dialog(getContext())

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_filter)

        val btnClose: AppCompatImageButton =
            dialog.findViewById<View>(R.id.btn_close_add) as AppCompatImageButton

        val btnFilter: AppCompatButton =
            dialog.findViewById<View>(R.id.btn_filter) as AppCompatButton


        val spinnerProject: Spinner =
            dialog.findViewById<View>(R.id.spinner_project) as Spinner
        val spinnerLevel: Spinner =
            dialog.findViewById<View>(R.id.spinner_level) as Spinner
        val spinnerAround: Spinner =
            dialog.findViewById<View>(R.id.spinner_around) as Spinner
        val spinnerTower: Spinner =
            dialog.findViewById<View>(R.id.spinner_tower) as Spinner
        val spinnerStatus: Spinner =
            dialog.findViewById<View>(R.id.spinner_status) as Spinner

        spinnerProject.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, PapersManager.getProjects)
        spinnerAround.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, PapersManager.getArounds)
        spinnerTower.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, PapersManager.getTowers)
        spinnerLevel.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, PapersManager.getLevels)
        spinnerStatus.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, PapersManager.getStatus)


        spinnerProject.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,view: View?,position: Int,id: Long) {
                val select = spinnerProject.selectedItem as ProjectResponse
                projectSelect = select.id.toString()
                projectSelectName = select.projectName
                projectIndex = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        spinnerAround.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,view: View?,position: Int,id: Long) {
                val select = spinnerAround.selectedItem as AroundResponse
                aroundSelect = select.id.toString()
                aroundSelectName = select.aroundName
                aroundIndex = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        spinnerTower.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,view: View?,position: Int,id: Long) {
                val select = spinnerTower.selectedItem as TowerResponse
                towerSelect = select.id.toString()
                towerSelectName = select.towerName
                towerIndex = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        spinnerLevel.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,view: View?,position: Int,id: Long) {
                val select = spinnerLevel.selectedItem as LevelResponse
                levelSelect = select.id.toString()
                levelSelectName = select.levelName
                levelIndex = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        spinnerStatus.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,view: View?,position: Int,id: Long) {
                val select = spinnerStatus.selectedItem as StatusResponse
                statusSelect = select.name
                statusSelectName = select.nameShow
                statusIndex = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        spinnerProject.setSelection(projectIndex)
        spinnerAround.setSelection(aroundIndex)
        spinnerTower.setSelection(towerIndex)
        spinnerLevel.setSelection(levelIndex)
        spinnerStatus.setSelection(statusIndex)


        btnFilter.setOnClickListener {
            var request = FilterDistributionRequest().apply {
                this.projectId = projectSelect
                this.aroundId = aroundSelect
                this.levelId = levelSelect
                this.stageId = towerSelect
                this.statusId = statusSelect
            }
            requestListDistribution(request)
            dialog.dismiss()
        }

        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.window?.attributes?.windowAnimations = R.style.AppTheme_Slide
        dialog.setCancelable(true)
        dialog.show()
    }

    private fun requestListDistribution(request: FilterDistributionRequest) {
        distributionPresenter.getListDistributionUseCase(FilterDistributionRequest().apply {
            this.projectId = request.projectId
            this.aroundId = request.aroundId
            this.levelId = request.levelId
            this.stageId = request.stageId
            this.statusId = request.statusId
        })
    }

    private fun getFilterData() {

        distributionPresenter.getListAroundUseCase(
            listener = { i, list  ->
                when (i) {
                    200 -> {
                        var data = list as ArrayList<AroundResponse>
                        data.add(0, AroundResponse("ACTIVO","Todos",0), )
                        PapersManager.getArounds = data
                    }
                    else -> {

                    }
                }
            })

        distributionPresenter.getListLevelUseCase(
            listener = { i, list  ->
                when (i) {
                    200 -> {
                        var data = list as ArrayList<LevelResponse>
                        data.add(0,LevelResponse("ACTIVO",0,"Todos"))
                        PapersManager.getLevels = data
                    }
                    else -> {

                    }
                }
            })

        distributionPresenter.getListTowerUseCase(
            listener = { i, list  ->
                when (i) {
                    200 -> {
                        var data = list as ArrayList<TowerResponse>
                        data.add(0, TowerResponse("ACTIVO",0,"Todos"))
                        PapersManager.getTowers = data
                    }
                    else -> {

                    }
                }
            })

        var listStatus : ArrayList<StatusResponse> = arrayListOf()
        listStatus.add(0, StatusResponse("ACTIVO",1,"","Todos"))
        listStatus.add( StatusResponse("ACTIVO",2,"free","Libre"))
        listStatus.add( StatusResponse("ACTIVO",3,"sold","Vendido"))
        listStatus.add( StatusResponse("ACTIVO",4,"separated","Separado"))
        PapersManager.getStatus = listStatus
    }

    private fun setAdapter() {
        @Suppress("SENSELESS_COMPARISON")
        if (distributionAdapter == null) {
            distributionAdapter = DistributionAdapter { status, distribution  ->
                when (status) {
                    0 -> {
                        requestSaleDetail(distribution)
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

            setDataCardTotal()
        } else {
            distributionAdapter!!.data = PapersManager.responseDistributions.listDistributions
            setDataCardTotal()
            distributionAdapter!!.notifyDataSetChanged()
        }
    }

    private fun requestSaleDetail(distribution: Distributions) {

        saleDetailPresenter.getSaleDetailUseCase(DistributionIdRequest().apply {
            this.distributionId = distribution.id
        },listener = { i, response  ->
                when (i) {
                    200 -> {
                        var data = response as SaleDetailResponse
                        PapersManager.responseSaleDetail = data

                        dialogSaleDetail(data)
                    }
                    else -> {

                    }
                }
            })

    }

    private fun setDataCardTotal() {


        binding.lnlFilter.tv_total_count.text =  PapersManager.responseDistributions.totalDistributions.distributionActive.toString()
        binding.lnlFilter.tv_total_free_count.text =  PapersManager.responseDistributions.totalDistributions.distributionFree.toString()
        binding.lnlFilter.tv_total_sold_count.text =  PapersManager.responseDistributions.totalDistributions.distributionSold.toString()
        binding.lnlFilter.tv_total_separated_count.text =  PapersManager.responseDistributions.totalDistributions.distributionSeparated.toString()

        if(PapersManager.responseDistributions.totalPercentage.activePercentage != ""){
            binding.lnlFilter.tv_total_percentage.text =
                PapersManager.responseDistributions.totalPercentage.activePercentage.replace("%","").trim().toDouble().roundToInt().toString()

            binding.lnlFilter.tv_total_free_percentage.text =
                PapersManager.responseDistributions.totalPercentage.freePercentage.replace("%","").trim().toDouble().roundToInt().toString()

            binding.lnlFilter.tv_total_sold_percentage.text =
                PapersManager.responseDistributions.totalPercentage.soldPercentage.replace("%","").trim().toDouble().roundToInt().toString()

            binding.lnlFilter.tv_total_separated_percentage.text =
                PapersManager.responseDistributions.totalPercentage.separatedPercentage.replace("%","").trim().toDouble().roundToInt().toString()
        }

        binding.lnlFilterSummary.tv_filter_around.text = aroundSelectName
        binding.lnlFilterSummary.tv_filter_level.text = levelSelectName
        binding.lnlFilterSummary.tv_filter_project.text =projectSelectName
        binding.lnlFilterSummary.tv_filter_stage.text = towerSelectName
        binding.lnlFilterSummary.tv_filter_status.text = statusSelectName

    }


    fun dialogSaleDetail(data: SaleDetailResponse) {

        val dialog = Dialog(getContext())

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_sale_detail)

        val btnClose: AppCompatImageButton =
            dialog.findViewById<View>(R.id.btn_close_add) as AppCompatImageButton

        val tvDistribution: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_distribution) as AppCompatTextView
        val tvAddress: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_address) as AppCompatTextView
        val tvStatusDistribution: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_status_distribution) as AppCompatTextView
        val tvIdentificator: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_identificator) as AppCompatTextView
        val tvStatusSale: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_status_sale) as AppCompatTextView
        val tvStatus: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_status) as AppCompatTextView
        val tvDate: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_date) as AppCompatTextView
        val tvClient: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_client) as AppCompatTextView
        val tvTypeDocument: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_type_document) as AppCompatTextView
        val tvSeller: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_seller) as AppCompatTextView
        val tvTypeContract: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_type_contract) as AppCompatTextView
        val tvTypePayment: AppCompatTextView =
            dialog.findViewById<View>(R.id.type_payment) as AppCompatTextView
        val tvInitial: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_initial) as AppCompatTextView
        val tvInterest: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_interest) as AppCompatTextView
        val tvNumberQuotas: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_number_quotas) as AppCompatTextView
        val tvAmountQuota: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_amount_quota) as AppCompatTextView
        val tvCurrency: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_currency) as AppCompatTextView
        val tvPaymentMethod: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_payment_method) as AppCompatTextView
        val tvPriceSoles: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_price_soles) as AppCompatTextView
        val tvPriceDollars: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_price_dollars) as AppCompatTextView
        val tvDiscountAmount: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_discount_amount) as AppCompatTextView
        val tvBono: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_bono) as AppCompatTextView
        val tvBonoAmount: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_bono_amount) as AppCompatTextView
        val tvTotalSoles: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_total_soles) as AppCompatTextView
        val tvTotalDollars: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_total_dollars) as AppCompatTextView
        val tvLastUpdate: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_last_update) as AppCompatTextView
        val tvRegister: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_register) as AppCompatTextView
        val tvRefinance: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_refinance) as AppCompatTextView

        val lnlExtraData: LinearLayout =
            dialog.findViewById<View>(R.id.lnl_all_data) as LinearLayout
        val tvTypeSale: AppCompatTextView =
            dialog.findViewById<View>(R.id.tv_type_sale) as AppCompatTextView



        tvDistribution.text = data.sale.distribution
        tvAddress.text = data.sale.address
        tvStatusDistribution.text = data.sale.statuDistribution
        tvIdentificator.text = data.sale.indentificator
        tvStatusSale.text = data.sale.statuSale
        tvStatus.text = data.sale.status
        tvDate.text = data.sale.date
        tvClient.text = data.sale.client
        tvTypeDocument.text = data.sale.dni
        tvSeller.text = data.sale.seller
        tvTypeContract.text = data.sale.typeContract
        tvTypePayment.text = data.sale.typePayment
        tvInitial.text = data.sale.initial
        tvInterest.text = data.sale.interest
        tvNumberQuotas.text = data.sale.numberQuotas.toString()
        tvAmountQuota.text = data.sale.amountQuota
        tvCurrency.text = data.sale.typeCurrency
        tvPaymentMethod.text = data.sale.mehotdPayment
        tvPriceSoles.text = data.sale.priceSoles
        tvPriceDollars.text = data.sale.priceDollars
        tvDiscountAmount.text = data.sale.discount

        tvBono.text = data.sale.bono
        tvBonoAmount.text = data.sale.amountBono
        tvTotalSoles.text = data.sale.totalSoles
        tvTotalDollars.text = data.sale.totalDollars
        tvLastUpdate.text = data.sale.lastUpdate
        tvRegister.text = data.sale.register
        tvRefinance.text = data.sale.refinance
        tvTypeSale.text = data.sale.typeSale

        if(data.sale.indentificator.equals("")){
            lnlExtraData.visibility = View.GONE
        }else{
            lnlExtraData.visibility = View.VISIBLE
        }

        btnClose.setOnClickListener {
            dialog.dismiss()
        }

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
        saleDetailPresenter.attachView(this)
        super.onResume()
    }

    @SuppressLint("MissingPermission")
    override fun onPause() {
        distributionPresenter.detachView()
        saleDetailPresenter.detachView()
        super.onPause()
    }


    override fun distributionSuccessPresenter(status: Int, vararg args: Serializable) {
        when (status) {
            200 -> {
                val response = args[0] as DistributionResponse
                if(response.status.equals("success")){
                    PapersManager.responseDistributions = response
                    binding.recycler.visibility = View.VISIBLE
                    binding.lnlEmpty.visibility = View.GONE
                    binding.lnlFilter.visibility = View.VISIBLE
                    binding.lnlFilterSummary.visibility = View.VISIBLE
                }else{
                    binding.lnlFilter.visibility = View.GONE
                    binding.lnlFilterSummary.visibility = View.GONE
                    binding.lnlEmpty.visibility = View.VISIBLE
                    binding.tvFailSearch.visibility = View.VISIBLE
                    binding.tvStartSearch.visibility = View.GONE
                    binding.recycler.visibility = View.GONE
                }

                setAdapter()
            }
            302 -> {
                binding.lnlFilter.visibility = View.GONE
                binding.lnlFilterSummary.visibility = View.GONE
                binding.lnlEmpty.visibility = View.VISIBLE
                binding.tvFailSearch.visibility = View.VISIBLE
                binding.tvStartSearch.visibility = View.GONE
                binding.recycler.visibility = View.GONE
            }
            else -> {
                binding.lnlFilter.visibility = View.GONE
                binding.lnlFilterSummary.visibility = View.GONE
                binding.lnlEmpty.visibility = View.VISIBLE
                binding.tvFailSearch.visibility = View.VISIBLE
                binding.tvStartSearch.visibility = View.GONE
                binding.recycler.visibility = View.GONE
            }
        }
    }

    override fun saleDetailSuccessPresenter(status: Int, vararg args: Serializable) {
        TODO("Not yet implemented")
    }

    override fun customTimeOut() {
        TODO("Not yet implemented")
    }


}
