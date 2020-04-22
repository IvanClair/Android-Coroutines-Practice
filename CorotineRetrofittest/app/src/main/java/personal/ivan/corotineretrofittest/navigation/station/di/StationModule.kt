package personal.ivan.corotineretrofittest.navigation.station.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import okhttp3.OkHttpClient
import personal.ivan.corotineretrofittest.MyApplication
import personal.ivan.corotineretrofittest.R
import personal.ivan.corotineretrofittest.api.ApiRepository
import personal.ivan.corotineretrofittest.api.ApiService
import personal.ivan.corotineretrofittest.di.ViewModelKey
import personal.ivan.corotineretrofittest.navigation.station.view.StationActivity
import personal.ivan.corotineretrofittest.navigation.station.viewmodel.StationViewModel
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Named

/* ------------------------------ Activity */

@Suppress("unused")
@Module
abstract class StationActivityModule {

    @ContributesAndroidInjector(
        modules = [
            StationViewModelModule::class,
            StationRepositoryModule::class]
    )
    abstract fun contributeStationActivity(): StationActivity
}

/* ------------------------------ ViewModel */

@Suppress("unused")
@Module
abstract class StationViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(StationViewModel::class)
    abstract fun bindStationViewModel(viewModel: StationViewModel): ViewModel
}

/* ------------------------------ Repository */

@Module
object StationRepositoryModule {

    /**
     * Create [Retrofit] service
     */
    @JvmStatic
    @Provides
    fun provideService(
        application: MyApplication,
        standardOkHttpClient: OkHttpClient,
        @Named("DEFAULT_CONVERTER") converterFactory: Converter.Factory
    ): ApiService =
        Retrofit.Builder()
            .baseUrl(application.getString(R.string.base_url))
            .client(standardOkHttpClient)
            .addConverterFactory(converterFactory)
            .build()
            .create(ApiService::class.java)

    /**
     * Create [ApiRepository]
     */
    @JvmStatic
    @Provides
    fun provideRepository(service: ApiService): ApiRepository = ApiRepository(mService = service)
}