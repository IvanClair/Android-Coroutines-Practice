package personal.ivan.corotineretrofittest.navigation.station.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import personal.ivan.corotineretrofittest.BR
import personal.ivan.corotineretrofittest.api.UBikeStation

/* ------------------------------ Page Binding Model */

class MainBindingModel : BaseObservable() {

    var showLoading: Boolean = true
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.showLoading)
        }
}

/* ------------------------------ View Holder Binding Model */

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