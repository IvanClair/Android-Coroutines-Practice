package personal.ivan.corotineretrofittest.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.android.ContributesAndroidInjector
import personal.ivan.corotineretrofittest.navigation.station.di.StationModule
import personal.ivan.corotineretrofittest.navigation.station.view.StationActivity
import kotlin.reflect.KClass

/* ------------------------------ Activity Module */

@Suppress("unused")
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [StationModule::class])
    abstract fun contributeMainActivity(): StationActivity
}

/* ------------------------------ ViewModel */

@Suppress("unused")
@Module
abstract class AppViewModelModule {

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