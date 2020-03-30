package personal.ivan.corotineretrofittest.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import personal.ivan.corotineretrofittest.R

class MainActivity : AppCompatActivity() {

    // View Model
    private val mViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    /* ------------------------------ Life Cycle */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel.mApiRs.observe(
            this,
            Observer {
                Log.i("", "")
            })
    }
}
