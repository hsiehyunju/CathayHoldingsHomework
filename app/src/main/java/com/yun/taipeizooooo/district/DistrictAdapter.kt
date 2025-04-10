package com.yun.taipeizooooo.district

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yun.taipeizooooo.databinding.ItemDistrictInfoBinding
import com.yun.taipeizooooo.models.DistrictData

class DistrictAdapter : ListAdapter<DistrictData, DistrictAdapter.DistrictViewHolder>(DIFF_CALLBACK) {

    inner class DistrictViewHolder(private val binding: ItemDistrictInfoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DistrictData) {
            binding.apply {
                imageView.loadImage(data.pictureUrl)
                tvTitle.text = data.name
                tvDesc.text = data.info
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DistrictViewHolder {
        val binding = ItemDistrictInfoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DistrictViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DistrictViewHolder,
        position: Int,
    ) {
        val data = getItem(position)
        holder.bind(data)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DistrictData>() {
            override fun areItemsTheSame(oldItem: DistrictData, newItem: DistrictData): Boolean {
                return oldItem.name == newItem.name // 或是用 ID
            }

            override fun areContentsTheSame(oldItem: DistrictData, newItem: DistrictData): Boolean {
                return oldItem == newItem
            }
        }
    }
}