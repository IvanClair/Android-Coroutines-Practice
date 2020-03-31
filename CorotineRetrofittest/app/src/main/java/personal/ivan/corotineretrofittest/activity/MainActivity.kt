package personal.ivan.corotineretrofittest.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

            // UBike station list API
            stationListApi.observe(
                this@MainActivity,
                Observer {
                    when (it.status) {
                        ApiStatus.LOADING -> enableLoading(enable = true)
                        ApiStatus.SUCCESS -> {
                            enableLoading(enable = false)
                        }
                        ApiStatus.FAIL -> {
                            enableLoading(enable = false)
                        }
                    }
                })
        }
    }
}
