package com.iml1s.epa

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.iml1s.epa.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var menu: Menu

    private val shareViewModel by viewModel<ShareViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            viewModel = initShareViewModel()
        }
        setSupportActionBar(binding.toolBar)
        supportActionBar?.title = ""
        setContentView(binding.root)

//        val navController = findNavController(R.id.fragment_container_view)
    }

    private fun initShareViewModel() = shareViewModel.apply {
        isNavigationBackButtonShow.onEach {
            supportActionBar?.apply {
                setDisplayShowHomeEnabled(true)
                setDisplayHomeAsUpEnabled(true)
            }
        }.launchIn(lifecycleScope)
    }


    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }
}