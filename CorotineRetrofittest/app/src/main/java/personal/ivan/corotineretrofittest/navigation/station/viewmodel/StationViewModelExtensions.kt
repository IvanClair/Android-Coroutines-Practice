package personal.ivan.corotineretrofittest.navigation.station.viewmodel

/**
 * Show or hide loading UI
 */
fun StationViewModel.showApiLoading(enable: Boolean) {
    stationBindingModel.value?.showLoading = enable
}