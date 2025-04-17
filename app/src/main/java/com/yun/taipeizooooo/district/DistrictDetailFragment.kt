package com.yun.taipeizooooo.district

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.yun.taipeizooooo.R
import com.yun.taipeizooooo.databinding.FragmentDistrictDetailBinding
import com.yun.taipeizooooo.databinding.ItemPlantOrAnimalItemBinding
import com.yun.taipeizooooo.events.TaipeiZooActivityEvents
import com.yun.taipeizooooo.extension.getFormattedUpdateDate
import com.yun.taipeizooooo.models.DistrictData
import com.yun.taipeizooooo.viewModels.TaipeiZooActivityViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class DistrictDetailFragment : Fragment() {

    private lateinit var binding: FragmentDistrictDetailBinding
    private val shareViewModel: TaipeiZooActivityViewModel by activityViewModel()
    private val detailData: DistrictData? by lazy {
        arguments?.getParcelable(DistrictData::javaClass.name, DistrictData::class.java)
    }

    companion object {
        fun getInstance(
            detailData: DistrictData
        ) : DistrictDetailFragment {
            return DistrictDetailFragment().apply {
                arguments = bundleOf(
                    DistrictData::javaClass.name to detailData
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDistrictDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initCollect()
    }

    private fun initView() {
        detailData?.let { data ->
            binding.collapsingToolbarLayout.title = data.name
            binding.glideImageView.loadImage(data.pictureUrl)
            binding.tvDistrictInfo.text = data.info
            data.memo?.let {
                if (it.isNotEmpty()) {
                    binding.tvMemo.text = it
                }
            }
            binding.tvUpdateTime.text = getString(
                R.string.district_detail_update_time_title,
                data.importDate.getFormattedUpdateDate()
            )
        }

        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    private fun initListener() {
        binding.btnGetMore.setOnClickListener {
            detailData?.url?.let {
                val intent = Intent(Intent.ACTION_VIEW, it.toUri())
                startActivity(intent)
            }
        }
    }

    private fun initCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                shareViewModel.animalData.collectLatest { animalList ->
                    val targetList = detailData?.name?.let { location ->
                        animalList.filter { it.location.contains(location) }
                    } ?: emptyList()

                    if (targetList.isNotEmpty()) {
                        binding.tvAnimalTitle.visibility = View.VISIBLE
                        binding.llAnimalItems.visibility = View.VISIBLE
                        binding.llAnimalItems.removeAllViews()
                        targetList.forEach { animal ->
                            val itemBinding = ItemPlantOrAnimalItemBinding.inflate(
                                layoutInflater,
                                binding.llAnimalItems, false)

                            itemBinding.glideImageView.loadImage(animal.pic01Url)
                            itemBinding.tvItemName.text = animal.nameCh
                            itemBinding.root.setOnClickListener {
                                shareViewModel.go(TaipeiZooActivityEvents.ToItemDetail(
                                    item = animal
                                ))
                            }
                            binding.llAnimalItems.addView(itemBinding.root)
                        }
                    } else {
                        binding.tvAnimalTitle.visibility = View.GONE
                        binding.llAnimalItems.visibility = View.GONE
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                shareViewModel.plantsData.collectLatest { plantList ->

                    val targetList = detailData?.name?.let { location ->
                        plantList.filter { it.location.contains(location) }
                    } ?: emptyList()

                    if (targetList.isNotEmpty()) {
                        binding.tvPlantTitle.visibility = View.VISIBLE
                        binding.llPlantItems.visibility = View.VISIBLE
                        binding.llPlantItems.removeAllViews()
                        targetList.forEach { plant ->
                            val itemBinding = ItemPlantOrAnimalItemBinding.inflate(
                                layoutInflater,
                                binding.llPlantItems, false)

                            itemBinding.glideImageView.loadImage(plant.pic01Url)
                            itemBinding.tvItemName.text = plant.nameCh
                            itemBinding.root.setOnClickListener {
                                shareViewModel.go(TaipeiZooActivityEvents.ToItemDetail(
                                    item = plant
                                ))
                            }
                            binding.llPlantItems.addView(itemBinding.root)
                        }
                    } else {
                        binding.tvPlantTitle.visibility = View.GONE
                        binding.llPlantItems.visibility = View.GONE
                    }
                }
            }
        }
    }
}