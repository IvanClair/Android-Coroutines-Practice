package personal.ivan.corotineretrofittest.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET
    fun getUBikeStationList(
        @Query("\$top") limit: Int,
        @Query("\$skip") index: Int
    ): Call<List<UBikeStation>>
}