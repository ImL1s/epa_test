package com.iml1s.epa.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iml1s.epa.databinding.FragmentEpaBinding
import com.iml1s.epa.main.viewModel.EpaViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class EpaFragment : Fragment() {

    private var _binding: FragmentEpaBinding? = null

    private val epaViewModel by viewModel<EpaViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentEpaBinding.inflate(inflater, container, false)
        .apply {
            _binding = this
            lifecycleOwner = viewLifecycleOwner
        }.root


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}