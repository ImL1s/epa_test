package com.iml1s.epa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iml1s.epa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val navController = findNavController(R.id.fragment_container_view)
    }
}