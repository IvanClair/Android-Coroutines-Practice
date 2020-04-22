package personal.ivan.corotineretrofittest.navigation.station.viewmodel

fun StationViewModel.showApiLoading(enable: Boolean) {
    mainBindingModel.value?.showLoading = enable
}