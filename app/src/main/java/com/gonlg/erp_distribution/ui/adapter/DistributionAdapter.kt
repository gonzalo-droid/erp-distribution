package com.gonlg.erp_distribution.ui.adapter

import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.gonlg.erp_distribution.R
import com.gonlg.erp_distribution.data.response.Distributions
import com.gonlg.erp_distribution.utils.inflate
import kotlinx.android.synthetic.main.item_distribution.view.*
import kotlin.random.Random

class DistributionAdapter  (private val listener: (Int, Distributions) -> Unit) : RecyclerView.Adapter<DistributionAdapter.CommentHolder>() {
    var data: ArrayList<Distributions> = arrayListOf()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: CommentHolder, position: Int) = holder.bind(data[position], listener)

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CommentHolder.init(parent, viewType)

    override fun getItemViewType(position: Int) = if (position == 0) 0 else 1

    class CommentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind( distribution: Distributions, listener: (Int, Distributions) -> Unit) = with(itemView) {

//            tv_ic_circle.backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.yellow))
            tv_title_distribution.text = distribution.distributionNameLevelAroundNumber

            var i = Random.nextInt(1,3)

            if(distribution.status.equals("Vendido")){
                tv_title_distribution.setTextColor(context.getColor(R.color.white))
                tv_title_distribution.setBackgroundColor(context.getColor(R.color.blue))
            }else if(distribution.status.equals("Incompleta")){
                tv_title_distribution.setTextColor(context.getColor(R.color.black))
                tv_title_distribution.setBackgroundColor(context.getColor(R.color.yellow))
            }else if(distribution.status.equals("Vencida")){
                tv_title_distribution.setTextColor(context.getColor(R.color.white))
                tv_title_distribution.setBackgroundColor(context.getColor(R.color.red))
            }else if(distribution.status.equals("Separado")){
                tv_title_distribution.setTextColor(context.getColor(R.color.white))
                tv_title_distribution.setBackgroundColor(context.getColor(R.color.orange))
            }else{
                tv_title_distribution.setTextColor(context.getColor(R.color.black))
                tv_title_distribution.setBackgroundColor(context.getColor(R.color.green))
            }


            tv_title_distribution.setOnClickListener {
                listener(0, distribution)
            }

        }

        companion object {
            fun init(parent: ViewGroup, viewType: Int) : DistributionAdapter.CommentHolder {
                val view = parent.inflate(R.layout.item_distribution)
                return DistributionAdapter.CommentHolder(view)
            }
        }
    }
}