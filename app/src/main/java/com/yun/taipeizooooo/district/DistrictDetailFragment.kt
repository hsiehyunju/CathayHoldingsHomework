package com.yun.taipeizooooo.district

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.yun.taipeizooooo.R
import com.yun.taipeizooooo.databinding.FragmentDistrictDetailBinding
import com.yun.taipeizooooo.extension.getFormattedUpdateDate
import com.yun.taipeizooooo.models.DistrictData
import androidx.core.net.toUri

class DistrictDetailFragment : Fragment() {

    private lateinit var binding: FragmentDistrictDetailBinding
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
                data.getFormattedUpdateDate()
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
}