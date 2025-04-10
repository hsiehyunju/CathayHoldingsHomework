package com.yun.taipeizooooo

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.yun.taipeizooooo.databinding.ActivityTaipeiZooBinding
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
            setSupportActionBar(toolbar)
            setContentView(root)
        }

        initCollect()

        if (savedInstanceState == null) {
            setSupportActionBarTitle(resId = R.string.home_title)
            goToFragment(DistrictFragment.newInstance())
        }
    }

    private fun initCollect() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.events.collectLatest {

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