package com.iml1s.epa.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iml1s.epa.main.model.AirData
import com.iml1s.epa.main.view.AirPollutionAdapter


@BindingAdapter("app:airPollutionHorizontalList")
fun setRecyclerAirPollutionHorizontalList(recyclerView: RecyclerView, data: List<AirData>) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter = AirPollutionAdapter(data)
        return
    }
    (recyclerView.adapter as AirPollutionAdapter).updateData(data)
}