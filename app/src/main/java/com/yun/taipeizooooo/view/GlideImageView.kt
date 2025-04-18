package com.yun.taipeizooooo.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.yun.taipeizooooo.R

class GlideImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val imageView = AppCompatImageView(context).apply {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT,
            Gravity.CENTER
        )
        scaleType = ImageView.ScaleType.CENTER_CROP
    }

    private val progressBar = ProgressBar(context).apply {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT,
            Gravity.CENTER
        )
    }

    init {
        addView(imageView)
        addView(progressBar)
    }

    fun loadImage(url: String) {
        progressBar.visibility = VISIBLE

        val fixedUrl = if (url.startsWith("http://")) {
            url.replaceFirst("http://", "https://")
        } else {
            url
        }

        Glide.with(this)
            .load(fixedUrl)
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean,
                ): Boolean {
                    target.onLoadFailed(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.broken_image
                        )
                    )
                    progressBar.visibility = GONE
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable?>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean,
                ): Boolean {
                    progressBar.visibility = GONE
                    return false
                }
            })
            .into(imageView)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Glide.with(this).clear(imageView)
    }
}