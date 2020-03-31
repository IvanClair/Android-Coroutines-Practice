package personal.ivan.corotineretrofittest.activity

fun MainViewModel.enableLoading(enable: Boolean) {
    mainBindingModel.value?.showLoading = enable
}