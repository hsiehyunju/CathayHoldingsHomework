package com.yun.taipeizooooo

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.yun.taipeizooooo.databinding.ActivityTaipeiZooBinding
import com.yun.taipeizooooo.district.DistrictDetailFragment
import com.yun.taipeizooooo.district.DistrictFragment
import com.yun.taipeizooooo.events.TaipeiZooActivityEvents
import com.yun.taipeizooooo.extension.toItemDetailUiData
import com.yun.taipeizooooo.itemdetail.ItemDetailFragment
import com.yun.taipeizooooo.viewModels.TaipeiZooActivityViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaipeiZooActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityTaipeiZooBinding.inflate(layoutInflater)
    }

    private val viewModel: TaipeiZooActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            setContentView(root)
        }

        initEdgeToEdge()
        initView()
        initData()
        initListener()
        initCollect()

        if (savedInstanceState == null) {
            goToFragment(DistrictFragment.newInstance())
        }
    }

    private fun initEdgeToEdge() {
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true // 黑色 icon
    }

    private fun initView() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.tvHomeTitle) { view, insets ->
            val statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top

            val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.topMargin = statusBarHeight
            view.layoutParams = layoutParams

            insets
        }
    }

    private fun initData() = viewModel.loadData()

    private fun initListener() {
        supportFragmentManager.addOnBackStackChangedListener {
            // 如果堆疊中有Fragment，就顯示返回按鈕
            val canGoBack = supportFragmentManager.backStackEntryCount > 0
            supportActionBar?.setDisplayHomeAsUpEnabled(canGoBack)
            supportActionBar?.setDisplayShowHomeEnabled(canGoBack)
            binding.tvHomeTitle.visibility = when (canGoBack) {
                true -> View.GONE
                else -> View.VISIBLE
            }
        }
    }

    private fun initCollect() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.events.collectLatest { event ->
                    when (event) {
                        is TaipeiZooActivityEvents.ToDistrictDetail -> {
                            addToFragment(
                                fragment = DistrictDetailFragment.getInstance(event.data)
                            )
                        }
                        is TaipeiZooActivityEvents.ToItemDetailWithAnimal -> {
                            addToFragment(
                                fragment = ItemDetailFragment.getInstance(
                                    uiData = event.item.toItemDetailUiData()
                                )
                            )
                        }
                        is TaipeiZooActivityEvents.ToItemDetailWithPlant -> {
                            addToFragment(
                                fragment = ItemDetailFragment.getInstance(
                                    uiData = event.item.toItemDetailUiData()
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    /**
     * Navigate to a fragment.
     */
    private fun goToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }

    private fun addToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(binding.fragmentContainer.id, fragment)
            .addToBackStack(null)
            .commit()
    }
}