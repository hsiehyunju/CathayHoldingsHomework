package com.yun.taipeizooooo.district

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.yun.taipeizooooo.R
import com.yun.taipeizooooo.databinding.FragmentItemDetailBinding
import com.yun.taipeizooooo.extension.getFormattedUpdateDate
import com.yun.taipeizooooo.models.Animal
import com.yun.taipeizooooo.models.Plant

class ItemDetailFragment : Fragment() {

    private val item: Any? by lazy {
        arguments?.getParcelable(KEY_ITEM, Plant::class.java) ?:
        arguments?.getParcelable(KEY_ITEM, Animal::class.java)
    }

    private lateinit var binding: FragmentItemDetailBinding

    companion object {
        const val KEY_ITEM = "key_item"

        fun getInstance(
            item: Any
        ) : ItemDetailFragment {
            return ItemDetailFragment().apply {
                arguments = bundleOf(
                    KEY_ITEM to item
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
    }

    private fun initView() {
        binding.toolbar.apply {
            title = getName()
            setNavigationOnClickListener {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
        }

        binding.glideImageView.loadImage(getPhotoUrl())
        binding.tvItemEnglishName.text = getEnglishName()
        binding.tvAlsoKnown.text = getAlsoKnown()
        binding.tvIntro.text = getIntro()
        binding.tvFeature.text = getFeature()
        binding.tvFunction.text = getFunction()
        binding.tvFunctionTitle.text = when (item) {
            is Animal -> getString(R.string.item_detail_function_animal)
            is Plant -> getString(R.string.item_detail_function_plant)
            else -> ""
        }

        binding.tvTime.text = getString(
            R.string.district_detail_update_time_title,
            getTime()
        )
    }

    private fun getName(): String {
        return when (item) {
            is Animal -> (item as Animal).nameCh
            is Plant -> (item as Plant).nameCh
            else -> ""
        }
    }

    private fun getEnglishName(): String {
        return when (item) {
            is Animal -> (item as Animal).nameEn
            is Plant -> (item as Plant).nameEn
            else -> ""
        }
    }

    private fun getAlsoKnown(): String {
        return when (item) {
            is Animal -> (item as Animal).alsoKnown
            is Plant -> (item as Plant).alsoKnown
            else -> ""
        }
    }

    private fun getIntro(): String {
        return when (item) {
            is Animal -> (item as Animal).habitat
            is Plant -> (item as Plant).brief
            else -> ""
        }
    }

    private fun getFeature(): String {
        return when (item) {
            is Animal -> (item as Animal).feature
            is Plant -> (item as Plant).feature
            else -> ""
        }
    }

    private fun getFunction(): String {
        return when (item) {
            is Animal -> (item as Animal).behavior
            is Plant -> (item as Plant).functionAndApplication
            else -> ""
        }
    }

    private fun getTime(): String {
        return when (item) {
            is Animal -> (item as Animal).importDate.getFormattedUpdateDate()
            is Plant -> (item as Plant).importDate.getFormattedUpdateDate()
            else -> "無"
        }
    }

    private fun getPhotoUrl(): String {
        return when (item) {
            is Animal -> (item as Animal).pic01Url
            is Plant -> (item as Plant).pic01Url
            else -> ""
        }
    }
}