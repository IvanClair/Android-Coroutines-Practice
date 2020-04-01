package personal.ivan.corotineretrofittest.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import personal.ivan.corotineretrofittest.api.ApiRepository
import personal.ivan.corotineretrofittest.api.ApiStatus

class MainViewModel : ViewModel() {

    /*
        API
     */
    private val mRepository = ApiRepository()
    private var mIndex: Int = 0
    val apiStatus: MutableLiveData<ApiStatus> = MutableLiveData()

    /*
        Binding Model
     */
    val mainBindingModel =
        MutableLiveData<MainBindingModel>().apply { value = MainBindingModel() }
    val mainVhBindingModelList =
        MutableLiveData<MutableList<MainVhBindingModel>>().apply { value = mutableListOf() }
    val dataUpdatedRange = MutableLiveData<Pair<Int, Int>>()

    /* ------------------------------ Initial */

    init {
        requestApi()
    }

    /* ------------------------------ API */

    private fun requestApi() {
        viewModelScope.launch {
            apiStatus.value = ApiStatus.LOADING
            try {
                val dataList = mRepository.requestStationList(index = mIndex)
                mainVhBindingModelList.value?.apply {
                    val lastIndexOfOriginDataList = lastIndex
                    addAll(MainVhBindingModel.createDataList(dataList = dataList))
                    dataUpdatedRange.value = Pair(lastIndexOfOriginDataList, dataList.size)
                }
                apiStatus.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                apiStatus.value = ApiStatus.FAIL
            }
        }
    }
}