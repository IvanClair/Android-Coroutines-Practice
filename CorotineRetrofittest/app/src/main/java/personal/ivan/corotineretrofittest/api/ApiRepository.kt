package personal.ivan.corotineretrofittest.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiRepository {

    companion object {
        private const val BASE_URL = "https://data.ntpc.gov.tw/od/data/api/"
        const val LIMIT: Int = 10
    }

    // API service
    private val mService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
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

    /**
     * Request UBike station list
     */
    suspend fun requestStationList(index: Int): List<UBikeStation> =
        mService.getUBikeStationList(limit = LIMIT, index = index)
}