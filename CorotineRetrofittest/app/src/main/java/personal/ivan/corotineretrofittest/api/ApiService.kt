package personal.ivan.corotineretrofittest.api

import retrofit2.http.GET

interface ApiService {

    @GET("54DDDC93-589C-4858-9C95-18B2046CC1FC?\$format=json")
    suspend fun getUBikeStationList(): List<UBikeStation>
}