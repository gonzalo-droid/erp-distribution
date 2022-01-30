package com.example.erp_distribution.ui.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.erp_distribution.R
import com.example.erp_distribution.data.response.Distributions
import com.example.erp_distribution.utils.inflate
import kotlinx.android.synthetic.main.item_distribution.view.*

class DistributionAdapter  (private val listener: (Int, Distributions, Int) -> Unit) : RecyclerView.Adapter<DistributionAdapter.CommentHolder>() {
    var data: ArrayList<Distributions> = arrayListOf()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: CommentHolder, position: Int) = holder.bind(data[position], listener)

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CommentHolder.init(parent, viewType)

    override fun getItemViewType(position: Int) = if (position == 0) 0 else 1

    class CommentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind( distribution: Distributions, listener: (Int, Distributions, Int) -> Unit) = with(itemView) {

            tv_title_distribution.text = distribution.levelAroundName
        }

        companion object {
            fun init(parent: ViewGroup, viewType: Int) : DistributionAdapter.CommentHolder {
                val view = parent.inflate(R.layout.item_distribution)
                return DistributionAdapter.CommentHolder(view)
            }
        }
    }
}