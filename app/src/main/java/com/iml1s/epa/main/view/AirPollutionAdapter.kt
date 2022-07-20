package com.iml1s.epa.main.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.iml1s.epa.BR
import com.iml1s.epa.R
import com.iml1s.epa.main.model.AirData

class AirPollutionAdapter(airDataList: List<AirData>) :
    RecyclerView.Adapter<AirPollutionViewHolder>() {

    private val airDataList = airDataList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_horizontal_air_data,
            parent,
            false
        ).let { AirPollutionViewHolder(it) }


    override fun onBindViewHolder(holder: AirPollutionViewHolder, position: Int) {
        holder.bind(airDataList[position])
    }

    override fun getItemCount(): Int = airDataList.count()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newAirDataList: List<AirData>) {
        this.airDataList.clear()
        notifyDataSetChanged()
        this.airDataList.addAll(newAirDataList)
        notifyDataSetChanged()
    }
}


class AirPollutionViewHolder(private val viewDataBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {

    fun bind(item: AirData) {
        viewDataBinding.apply {
            setVariable(BR.item, item)
            executePendingBindings()
        }
    }
}