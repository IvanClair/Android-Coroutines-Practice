package personal.ivan.corotineretrofittest.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import personal.ivan.corotineretrofittest.MyApplication
import personal.ivan.corotineretrofittest.navigation.station.di.StationActivityModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelModule::class,
        RetrofitModule::class,
        StationActivityModule::class]
)
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MyApplication): AppComponent
    }
}