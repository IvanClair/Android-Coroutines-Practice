package personal.ivan.corotineretrofittest.activity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import personal.ivan.corotineretrofittest.BR

class MainBindingModel : BaseObservable() {

    var showLoading: Boolean = true
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.showLoading)
        }
}