package com.iml1s.epa.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.iml1s.epa.ShareViewModel
import com.iml1s.epa.databinding.FragmentEpaBinding
import com.iml1s.epa.main.viewModel.EpaViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
            viewModel = initEpaViewModel()
            shareViewModel = this@EpaFragment.shareViewModel.initSubscribe()
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initEpaViewModel() = epaViewModel.apply {
        toastSource.onEach {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun ShareViewModel.initSubscribe() = apply {
        searchViewQueryText.onEach {
            epaViewModel.userQuerySource.emit(it)
        }.launchIn(epaViewModel.viewModelScope)

        isNavigationBackButtonShow.onEach {
            epaViewModel.isNavigationBackButtonShow.emit(it)
        }.launchIn(epaViewModel.viewModelScope)
    }
}