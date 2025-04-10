 package com.yun.taipeizooooo.district

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var adapter: DistrictAdapter
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
        adapter = DistrictAdapter()
        initCollect()
        initView()
        initData()
    }

    private fun initView() {
        binding.rvDistrictList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDistrictList.adapter = adapter
        binding.rvDistrictList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                // 最後一個 item 可見觸發
                if (totalItemCount > 0 && lastVisibleItem >= totalItemCount - 1) {
                    viewModel.fetchDistrictData()
                }
            }
        })
    }

    private fun initData() = viewModel.fetchDistrictData()

    private fun initCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest {
                    when (it) {
                        is DistrictUiState.Loading -> {
//                            binding.testbtn.text = "Loading..."
                        }

                        is DistrictUiState.Success -> {
                            adapter.submitList(it.districts)
//                            binding.testbtn.text = "${it.districts.count()}"
                        }

                        is DistrictUiState.Failure -> {
//                            binding.testbtn.text = "Error"
                        }
                    }
                }
            }
        }
    }
}