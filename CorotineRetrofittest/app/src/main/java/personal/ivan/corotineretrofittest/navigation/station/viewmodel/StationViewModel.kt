package personal.ivan.corotineretrofittest.navigation.station.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import personal.ivan.corotineretrofittest.navigation.station.model.MainBindingModel
import personal.ivan.corotineretrofittest.navigation.station.model.MainVhBindingModel
import personal.ivan.corotineretrofittest.api.ApiStatus
import javax.inject.Inject

class StationViewModel @Inject constructor() : ViewModel() {

    /*
        API
     */
    private var mIndex: Int = 0
    val apiStatus = MutableLiveData<ApiStatus>()

    /*
        Binding Model
     */
    val mainBindingModel =
        MutableLiveData<MainBindingModel>().apply { value =
            MainBindingModel()
        }
    val mainVhBindingModelList =
        MutableLiveData<MutableList<MainVhBindingModel>>().apply { value = mutableListOf() }
    val dataUpdatedRange = MutableLiveData<Pair<Int, Int>>()

    /* ------------------------------ Initial */

    init {
        requestApi()
    }

    /* ------------------------------ API */

    private fun requestApi() {
//        viewModelScope.launch {
//            apiStatus.value = ApiStatus.LOADING
//            try {
//                val dataList = mRepository.requestStationList(index = mIndex)
//                mainVhBindingModelList.value?.apply {
//                    val lastIndexOfOriginDataList = lastIndex
//                    addAll(MainVhBindingModel.createDataList(dataList = dataList))
//                    dataUpdatedRange.value = Pair(lastIndexOfOriginDataList, dataList.size)
//                }
//                apiStatus.value = ApiStatus.SUCCESS
//            } catch (e: Exception) {
//                apiStatus.value = ApiStatus.FAIL
//            }
//        }
    }
}