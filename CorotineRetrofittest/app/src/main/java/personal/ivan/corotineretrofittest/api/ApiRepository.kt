package personal.ivan.corotineretrofittest.api

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import personal.ivan.corotineretrofittest.R
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * @property mContext could be application context, no need to use UI context
 */
class ApiRepository(private val mContext: Context) {

    // API service
    private val mService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(mContext.getString(R.string.base_url))
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

    suspend fun requestStationList() {

    }
}