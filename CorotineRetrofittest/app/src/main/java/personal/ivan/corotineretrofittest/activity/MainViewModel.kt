package personal.ivan.corotineretrofittest.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import personal.ivan.corotineretrofittest.api.ApiRepository
import personal.ivan.corotineretrofittest.api.UBikeStation

class MainViewModel : ViewModel() {

    // Repository
    private val mRepository: ApiRepository by lazy {
        ApiRepository()
    }

    // API Response
    val mApiRs: MutableLiveData<List<UBikeStation>> = MutableLiveData()

    init {
        viewModelScope.launch {
            mApiRs.value = mRepository.requestStationList(limit = 10, index = 0)
        }
    }
}