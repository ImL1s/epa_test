package com.iml1s.epa.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iml1s.epa.ShareViewModel
import com.iml1s.epa.databinding.FragmentEpaBinding
import com.iml1s.epa.main.viewModel.EpaViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class EpaFragment : Fragment() {

    private val epaViewModel by viewModel<EpaViewModel>()
    private val shareViewModel by sharedViewModel<ShareViewModel>()

    private var binding: FragmentEpaBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentEpaBinding.inflate(inflater, container, false)
        .apply {
            binding = this
            lifecycleOwner = viewLifecycleOwner
        }.root


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}