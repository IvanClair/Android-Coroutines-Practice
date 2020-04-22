package personal.ivan.corotineretrofittest.navigation.station.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import personal.ivan.corotineretrofittest.api.ApiRepository
import personal.ivan.corotineretrofittest.api.ApiStatus
import personal.ivan.corotineretrofittest.api.UBikeStation
import personal.ivan.corotineretrofittest.navigation.station.model.MainBindingModel
import personal.ivan.corotineretrofittest.navigation.station.model.MainVhBindingModel
import javax.inject.Inject

class StationViewModel @Inject constructor(private val mRepository: ApiRepository) : ViewModel() {

    /*
        API
     */
    val apiStatus = MutableLiveData<ApiStatus>()

    /*
        Binding Model
     */
    val stationBindingModel =
        MutableLiveData<MainBindingModel>().apply {
            value = MainBindingModel()
        }
    val stationVhBindingModelList = MutableLiveData<List<UBikeStation>>()

    /* ------------------------------ Initial */

    init {
        requestApi()
    }

    /* ------------------------------ API */

    private fun requestApi() {
        viewModelScope.launch {
            apiStatus.value = ApiStatus.LOADING
            try {
                stationVhBindingModelList.value = mRepository.requestStationList()
                apiStatus.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                apiStatus.value = ApiStatus.FAIL
            }
        }
    }
}