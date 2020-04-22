package personal.ivan.corotineretrofittest.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiRepository {

    companion object {
        const val LIMIT: Int = 10
    }

    private val mService: ApiService =
        Retrofit.Builder()
            .baseUrl("application.getString(R.string.base_url)")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient
                    .Builder()
                    .apply {
                        addInterceptor(
                            HttpLoggingInterceptor()
                                .apply { level = HttpLoggingInterceptor.Level.BODY })
                    }
                    .build())
            .build()
            .create(ApiService::class.java)

    /**
     * Request UBike station list
     */
    suspend fun requestStationList(index: Int): List<UBikeStation> =
        mService.getUBikeStationList(limit = LIMIT, index = index)
}