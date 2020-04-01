package personal.ivan.corotineretrofittest.activity

fun MainViewModel.showApiLoading(enable: Boolean) {
    mainBindingModel.value?.showLoading = enable
}