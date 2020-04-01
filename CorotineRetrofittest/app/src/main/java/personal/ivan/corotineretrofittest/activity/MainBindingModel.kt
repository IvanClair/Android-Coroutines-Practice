package personal.ivan.corotineretrofittest.activity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import personal.ivan.corotineretrofittest.BR
import personal.ivan.corotineretrofittest.api.UBikeStation

class MainBindingModel : BaseObservable() {

    var showLoading: Boolean = true
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.showLoading)
        }
}

data class MainVhBindingModel(
    val closed: Boolean,
    val area: String,
    val name: String,
    val availableNumber: String,
    val spaceForReturn: String
) {
    companion object {

        /**
         * Create binding model list for view holder
         */
        fun createDataList(dataList: List<UBikeStation>): List<MainVhBindingModel> =
            dataList.map {
                MainVhBindingModel(
                    closed = it.closed?.toBoolean() ?: false,
                    area = it.stationAreaEn ?: "",
                    name = it.stationNameEn ?: "",
                    availableNumber = it.availableForRental ?: "",
                    spaceForReturn = it.availableSpaceForReturn ?: ""
                )
            }
    }
}