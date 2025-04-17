package com.yun.taipeizooooo

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.yun.taipeizooooo.databinding.ActivityTaipeiZooBinding
import com.yun.taipeizooooo.district.DistrictDetailFragment
import com.yun.taipeizooooo.district.DistrictFragment
import com.yun.taipeizooooo.district.ItemDetailFragment
import com.yun.taipeizooooo.events.TaipeiZooActivityEvents
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

        initView()
        initListener()
        initCollect()

        if (savedInstanceState == null) {
            goToFragment(DistrictFragment.newInstance())
        }
    }

    private fun initView() {
//        binding.toolbar.setNavigationOnClickListener {
//            onBackPressedDispatcher.onBackPressed()
//        }
//        ViewCompat.setOnApplyWindowInsetsListener(binding.toolbar) { view, insets ->
//            val statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
//
//            val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
//            layoutParams.topMargin = statusBarHeight
//            view.layoutParams = layoutParams
//
//            insets
//        }
    }

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
                            supportFragmentManager.beginTransaction()
                                .add(binding.fragmentContainer.id, DistrictDetailFragment.getInstance(event.data))
                                .addToBackStack(null)
                                .commit()
                        }
                        is TaipeiZooActivityEvents.ToItemDetail -> {
                            supportFragmentManager.beginTransaction()
                                .add(binding.fragmentContainer.id, ItemDetailFragment.getInstance(event.item))
                                .addToBackStack(null)
                                .commit()
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

    /** Set the title of the support action bar. */
    private fun setSupportActionBarTitle(@StringRes resId: Int) = supportActionBar?.setTitle(resId)
}