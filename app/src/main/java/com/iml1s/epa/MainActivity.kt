package com.iml1s.epa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.iml1s.epa.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val shareViewModel by viewModel<ShareViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            viewModel = initShareViewModel()
        }
        setSupportActionBar(binding.toolBar)
        supportActionBar?.title = getString(R.string.air_pollution)
        setContentView(binding.root)
    }

    private fun initShareViewModel() = shareViewModel.apply {
        isNavigationBackButtonShow
            .onEach { this@MainActivity.showBackButton(it) }
            .launchIn(lifecycleScope)
    }

    override fun onSupportNavigateUp(): Boolean {
        collapsedSearchView()
        shareViewModel.showBackButton(false)
        return super.onSupportNavigateUp()
    }

    private fun collapsedSearchView() {
        binding.searchView.apply {
            setQuery("", false)
            setIconifiedByDefault(true)
            onActionViewCollapsed()
        }
    }

    private fun showBackButton(isShow: Boolean) {
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(isShow)
            setDisplayHomeAsUpEnabled(isShow)
        }
    }
}