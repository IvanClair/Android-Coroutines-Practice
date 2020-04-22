package personal.ivan.corotineretrofittest.navigation.station.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.android.support.DaggerAppCompatActivity
import personal.ivan.corotineretrofittest.R
import personal.ivan.corotineretrofittest.api.ApiStatus
import personal.ivan.corotineretrofittest.databinding.ActivityStationBinding
import personal.ivan.corotineretrofittest.di.AppViewModelFactory
import personal.ivan.corotineretrofittest.navigation.station.viewmodel.StationViewModel
import personal.ivan.corotineretrofittest.navigation.station.viewmodel.showApiLoading
import javax.inject.Inject

class StationActivity : DaggerAppCompatActivity() {

    // View Model
    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val mViewModel: StationViewModel by viewModels { viewModelFactory }

    // Data Binding
    private val mBinding: ActivityStationBinding by lazy {
        DataBindingUtil
            .setContentView<ActivityStationBinding>(this, R.layout.activity_station)
            .apply { viewModel = mViewModel }
    }

    /* ------------------------------ Life Cycle */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        observeLiveData()
    }

    /* ------------------------------ UI */

    private fun initRecyclerView() {
        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = StationListAdapter(mViewModel = mViewModel)
        }
    }

    private fun updateRecyclerView() {
        mBinding.recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun showApiAlert() {
        mViewModel.showApiLoading(enable = false)
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.api_alert_title)
            .setMessage(R.string.api_alert_message)
            .setPositiveButton(R.string.button_ok, null)
            .show()
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

            // required data set changed
            stationVhBindingModelList.observe(
                this@StationActivity,
                Observer { updateRecyclerView() })
        }
    }
}
