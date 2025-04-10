package com.yun.taipeizooooo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.yun.taipeizooooo.databinding.FragmentDistrictBinding
import com.yun.taipeizooooo.events.DistrictUiState
import com.yun.taipeizooooo.viewModels.DistrictViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 台北動物園管區簡介 Fragment
 */
class DistrictFragment : Fragment() {

    private lateinit var binding: FragmentDistrictBinding
    private val viewModel: DistrictViewModel by viewModel()

    companion object {
        fun newInstance(): DistrictFragment {
            return DistrictFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDistrictBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initCollect()
    }

    private fun initView() {
        binding.testbtn.setOnClickListener {
            viewModel.fetchDistrictData()
        }
    }
    private fun initData() {

    }

    private fun initCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest {
                    when (it) {
                        is DistrictUiState.Loading -> {
                            binding.testbtn.text = "Loading..."
                        }

                        is DistrictUiState.Success -> {
                            binding.testbtn.text = "${it.districts.count()}"
                        }

                        is DistrictUiState.Failure -> {
                            binding.testbtn.text = "Error"
                        }
                    }
                }
            }
        }
    }







}