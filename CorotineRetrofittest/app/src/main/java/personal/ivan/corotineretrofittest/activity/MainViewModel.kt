package personal.ivan.corotineretrofittest.activity

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import personal.ivan.corotineretrofittest.api.ApiRepository
import personal.ivan.corotineretrofittest.api.ApiResult
import personal.ivan.corotineretrofittest.api.UBikeStation

class MainViewModel : ViewModel() {

    // Repository
    private val mRepository: ApiRepository = ApiRepository()

    // API
    val mIndex: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = 0 }
    val stationListApi: LiveData<ApiResult<List<UBikeStation>>> =
        mIndex.switchMap {
            liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
                emit(ApiResult.loading())
                try {
                    emit(ApiResult.success(data = mRepository.requestStationList(index = it)))
                } catch (e: Exception) {
                    emit(ApiResult.fail())
                }
            }
        }

    // Binding Model
    val mainBindingModel: MutableLiveData<MainBindingModel> =
        MutableLiveData<MainBindingModel>().apply { value = MainBindingModel() }
}