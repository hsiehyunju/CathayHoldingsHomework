 package com.yun.taipeizooooo.district

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yun.taipeizooooo.databinding.FragmentDistrictBinding
import com.yun.taipeizooooo.events.DistrictUiState
import com.yun.taipeizooooo.events.TaipeiZooActivityEvents
import com.yun.taipeizooooo.viewModels.DistrictViewModel
import com.yun.taipeizooooo.viewModels.TaipeiZooActivityViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 台北動物園管區簡介 Fragment
 */
class DistrictFragment : Fragment() {

    private lateinit var binding: FragmentDistrictBinding
    private lateinit var adapter: DistrictAdapter
    private val shareViewModel by activityViewModel<TaipeiZooActivityViewModel>()
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
    ): View {
        binding = FragmentDistrictBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DistrictAdapter {
            shareViewModel.go(
                TaipeiZooActivityEvents.ToDistrictDetail(
                    data = it
                )
            )
        }
        initCollect()
        initView()
        initEdgeToEdge()
        initData()
    }

    private fun initView() {
        binding.rvDistrictList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDistrictList.adapter = adapter
        binding.rvDistrictList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (viewModel.getCanNextPage()) {
                    val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return
                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                    // 最後一個 item 可見觸發拿下一頁
                    if (totalItemCount > 0 && lastVisibleItem >= totalItemCount - 1) {
                        binding.loadMoreProgress.visibility = View.VISIBLE
                        viewModel.fetchDistrictData()
                    }
                }
            }
        })
    }

    private fun initEdgeToEdge() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.edge2EdgeSpacer) { view, insets ->
            val barHeight = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom

            val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.bottomMargin = barHeight
            view.layoutParams = layoutParams

            insets
        }
    }

    private fun initData() = viewModel.fetchDistrictData()

    private fun initCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest {
                    when (it) {
                        is DistrictUiState.Loading -> {
                            binding.progress.visibility = View.VISIBLE
                        }

                        is DistrictUiState.Success -> {
                            binding.progress.visibility = View.INVISIBLE
                            adapter.submitList(it.districts)
                            binding.loadMoreProgress.visibility = View.GONE
                        }

                        is DistrictUiState.Failure -> {
                            binding.progress.visibility = View.INVISIBLE
//                            binding.testbtn.text = "Error"
                        }
                    }
                }
            }
        }
    }
}