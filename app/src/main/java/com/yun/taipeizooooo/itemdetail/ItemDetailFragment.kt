package com.yun.taipeizooooo.itemdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.yun.taipeizooooo.R
import com.yun.taipeizooooo.databinding.FragmentItemDetailBinding
import com.yun.taipeizooooo.utils.StringUtils

class ItemDetailFragment : Fragment() {

    private val data: ItemDetailUiData by lazy {
        arguments?.getParcelable(
            ItemDetailUiData::class.java.name,
            ItemDetailUiData::class.java
        ) ?: ItemDetailUiData()
    }

    private lateinit var binding: FragmentItemDetailBinding

    companion object {
        fun getInstance(
            uiData: ItemDetailUiData
        ) : ItemDetailFragment {
            return ItemDetailFragment().apply {
                arguments = bundleOf(
                    ItemDetailUiData::class.java.name to uiData
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initEdge2Edge()
    }

    private fun initView() {
        binding.toolbar.apply {
            title = data.name
            setNavigationOnClickListener {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
        }

        with(data) {
            binding.apply {
                glideImageView.loadImage(imageUrl)
                tvItemEnglishName.text = englishName
                tvAlsoKnown.text = alsoKnown
                tvIntro.text = intro
                tvFeature.text = feature
                tvFunction.text = function
                tvFunctionTitle.text = functionTitle
                tvTime.text = StringUtils.getString(
                    R.string.district_detail_update_time_title,
                    updateTime
                )
            }
        }
    }

    private fun initEdge2Edge() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.appBarLayout) { view, insets ->
            val barHeight = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top

            val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.topMargin = barHeight
            view.layoutParams = layoutParams

            insets
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.scrollView) { view, insets ->
            val barHeight = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom

            val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.bottomMargin = barHeight
            view.layoutParams = layoutParams

            insets
        }
    }
}