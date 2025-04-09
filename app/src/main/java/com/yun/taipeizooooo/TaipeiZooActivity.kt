package com.yun.taipeizooooo

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.yun.taipeizooooo.databinding.ActivityTaipeiZooBinding

class TaipeiZooActivity : ComponentActivity() {

    private val binding by lazy {
        ActivityTaipeiZooBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }





}