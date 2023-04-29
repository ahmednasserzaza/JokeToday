package com.fighter.joketoday.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fighter.joketoday.R
import com.fighter.joketoday.databinding.ActivityMainBinding
import com.fighter.joketoday.databinding.TestLayoutBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: TestLayoutBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.test_layout)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

//        viewModel.randomJoke.observe(this , {
//            binding.textVal.text = it.setup
//        })
    }

}