package personal.ivan.corotineretrofittest.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import personal.ivan.corotineretrofittest.R
import personal.ivan.corotineretrofittest.api.ApiStatus
import personal.ivan.corotineretrofittest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // View Model
    private val mViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    // Data Binding
    private val mBinding: ActivityMainBinding by lazy {
        DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply { viewModel = mViewModel }
    }

    /* ------------------------------ Life Cycle */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding
        observeLiveData()
    }

    /* ------------------------------ Observe LiveData */

    private fun observeLiveData() {
        mViewModel.apply {

            // API loading status
            apiStatus.observe(
                this@MainActivity,
                Observer {
                    @Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
                    when (it) {
                        ApiStatus.LOADING -> showApiLoading(enable = true)
                        ApiStatus.SUCCESS -> showApiLoading(enable = false)
                        ApiStatus.FAIL -> showApiAlert()
                    }
                })
        }
    }

    /* ------------------------------ UI */

    private fun showApiAlert() {
        mViewModel.showApiLoading(enable = false)
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.api_alert_title)
            .setMessage(R.string.api_alert_message)
            .setPositiveButton(R.string.button_ok, null)
            .show()
    }
}
