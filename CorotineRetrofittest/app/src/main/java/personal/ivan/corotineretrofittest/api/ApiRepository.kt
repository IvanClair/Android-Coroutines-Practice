package personal.ivan.corotineretrofittest.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiRepository() {

    // API service
    private val mService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://data.ntpc.gov.tw/od/data/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient
                    .Builder()
                    .apply {
                        addInterceptor(HttpLoggingInterceptor()
                            .apply { level = HttpLoggingInterceptor.Level.BODY })
                    }
                    .build())
            .build()
            .create(ApiService::class.java)
    }

    /* ------------------------------ API */

    suspend fun requestStationList(
        limit: Int,
        index: Int
    ): List<UBikeStation> = mService.getUBikeStationList(limit = limit, index = index)
}