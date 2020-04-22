package personal.ivan.corotineretrofittest.navigation.station.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.android.support.DaggerAppCompatActivity
import personal.ivan.corotineretrofittest.R
import personal.ivan.corotineretrofittest.api.ApiStatus
import personal.ivan.corotineretrofittest.databinding.ActivityMainBinding
import personal.ivan.corotineretrofittest.di.AppViewModelFactory
import personal.ivan.corotineretrofittest.navigation.station.viewmodel.StationViewModel
import personal.ivan.corotineretrofittest.navigation.station.viewmodel.showApiLoading
import javax.inject.Inject

class StationActivity : DaggerAppCompatActivity() {

    // View Model
    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val mViewModel: StationViewModel by viewModels { viewModelFactory }
//    private val mViewModel: StationViewModel by viewModels()

    // Data Binding
    private val mBinding: ActivityMainBinding by lazy {
        DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply { viewModel = mViewModel }
    }

    /* ------------------------------ Life Cycle */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        observeLiveData()
    }

    /* ------------------------------ Observe LiveData */

    private fun observeLiveData() {
        mViewModel.apply {

            // API loading status
            apiStatus.observe(
                this@StationActivity,
                Observer {
                    @Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
                    when (it) {
                        ApiStatus.LOADING -> showApiLoading(enable = true)
                        ApiStatus.SUCCESS -> showApiLoading(enable = false)
                        ApiStatus.FAIL -> showApiAlert()
                    }
                })

            // Data set changed
            dataUpdatedRange.observe(
                this@StationActivity,
                Observer { updateRecyclerView(startIndex = it.first, count = it.second) })
        }
    }

    /* ------------------------------ UI */

    private fun initRecyclerView() {
        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            adapter =
                StationListAdapter(
                    mViewModel = mViewModel
                )
        }
    }

    private fun updateRecyclerView(
        startIndex: Int,
        count: Int
    ) {
        mBinding.recyclerView.adapter?.notifyItemRangeChanged(startIndex, count)
    }

    private fun showApiAlert() {
        mViewModel.showApiLoading(enable = false)
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.api_alert_title)
            .setMessage(R.string.api_alert_message)
            .setPositiveButton(R.string.button_ok, null)
            .show()
    }
}
