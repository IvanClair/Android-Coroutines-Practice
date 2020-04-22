package personal.ivan.corotineretrofittest.navigation.station.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import personal.ivan.corotineretrofittest.BR
import personal.ivan.corotineretrofittest.R
import personal.ivan.corotineretrofittest.navigation.station.viewmodel.StationViewModel

/* ------------------------------ Adapter */

class StationListAdapter(private val mViewModel: StationViewModel) :
    RecyclerView.Adapter<StationViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) =
        StationViewHolder(
            mBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_station,
                parent,
                false
            )
        )

    override fun onBindViewHolder(
        holder: StationViewHolder,
        position: Int
    ) {
        mViewModel.stationVhBindingModelList.value
            ?.getOrNull(position)
            ?.also { holder.bind(position = position, viewModel = mViewModel) }
    }

    override fun getItemCount(): Int =
        mViewModel.stationVhBindingModelList.value?.size ?: 0
}

/* ------------------------------ ViewHolder */

class StationViewHolder(private val mBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(mBinding.root) {

    /**
     * Set up data to view
     */
    fun bind(
        position: Int? = null,
        viewModel: ViewModel? = null
    ) {
        mBinding.apply {
            position?.also { setVariable(BR.index, it) }
            viewModel?.also { setVariable(BR.viewModel, it) }
            executePendingBindings()
        }
    }
}