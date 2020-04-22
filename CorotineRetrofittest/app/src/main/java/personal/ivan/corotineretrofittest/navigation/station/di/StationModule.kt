package personal.ivan.corotineretrofittest.navigation.station.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import personal.ivan.corotineretrofittest.di.ViewModelKey
import personal.ivan.corotineretrofittest.navigation.station.viewmodel.StationViewModel

@Suppress("unused")
@Module
abstract class StationModule {

    @Binds
    @IntoMap
    @ViewModelKey(StationViewModel::class)
    abstract fun bindViewModel(viewModel: StationViewModel): ViewModel
}