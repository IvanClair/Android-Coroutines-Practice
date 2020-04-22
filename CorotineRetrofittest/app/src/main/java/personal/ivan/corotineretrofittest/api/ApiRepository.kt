package personal.ivan.corotineretrofittest.api

import javax.inject.Inject

class ApiRepository @Inject constructor(private val mService: ApiService) {

    /**
     * Request UBike station list
     */
    suspend fun requestStationList(): List<UBikeStation> =
        mService.getUBikeStationList()
}