package personal.ivan.corotineretrofittest.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import personal.ivan.corotineretrofittest.BuildConfig
import personal.ivan.corotineretrofittest.navigation.station.di.StationRepositoryModule
import personal.ivan.corotineretrofittest.navigation.station.di.StationViewModelModule
import personal.ivan.corotineretrofittest.navigation.station.view.StationActivity
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import kotlin.reflect.KClass

/* ------------------------------ ViewModel */

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

/* ------------------------------ Retrofit */

@Module
object RetrofitModule {

    /**
     * Standard [OkHttpClient]
     *
     * Note :
     * log HTTP body while run on debug mode
     * 5 seconds for connect timeout
     * 5 seconds for read timeout
     */
    @JvmStatic
    @Singleton
    @Provides
    fun provideStandardOkHttpClient(
        @Named("DEFAULT_OK_HTTP_CLIENT_BUILDER") builder: OkHttpClient.Builder
    ): OkHttpClient =
        builder
            .also {
                it.connectTimeout(5L, TimeUnit.SECONDS)
                it.readTimeout(5L, TimeUnit.SECONDS)
            }
            .build()

    /**
     * An [OkHttpClient.Builder] can be customized properties
     */
    @JvmStatic
    @Singleton
    @Provides
    @Named("DEFAULT_OK_HTTP_CLIENT_BUILDER")
    fun provideOkHttpClientBuilder(interceptor: HttpLoggingInterceptor): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(interceptor)
                }
            }

    /**
     * HTTP log interceptor for detailed HTTP information
     */
    @JvmStatic
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }

    /**
     * Using Moshi as default converter for APIs
     */
    @JvmStatic
    @Singleton
    @Provides
    @Named("DEFAULT_CONVERTER")
    fun provideConverterFactory(): Converter.Factory = MoshiConverterFactory.create()
}

