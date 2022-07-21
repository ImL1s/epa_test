package com.iml1s.epa.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iml1s.epa.main.model.AirData
import com.iml1s.epa.main.view.AirPollutionAdapterHorizontal
import com.iml1s.epa.main.view.AirPollutionAdapterVertical
import com.iml1s.epa.main.viewModel.EpaViewModel


@BindingAdapter("app:airPollutionHorizontalList")
fun setRecyclerAirPollutionHorizontalList(recyclerView: RecyclerView, data: List<AirData>) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter = AirPollutionAdapterHorizontal(data)
        return
    }
    (recyclerView.adapter as? AirPollutionAdapterHorizontal)?.updateData(data)
}


@BindingAdapter("app:airPollutionVerticalList", "app:epaViewModel", requireAll = true)
fun setRecyclerAirPollutionVerticalList(
    recyclerView: RecyclerView,
    data: List<AirData>,
    epaViewModel: EpaViewModel
) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter = AirPollutionAdapterVertical(data, epaViewModel)
        return
    }
    (recyclerView.adapter as? AirPollutionAdapterVertical)?.updateData(data)
}