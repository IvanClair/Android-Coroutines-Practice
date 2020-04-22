package personal.ivan.corotineretrofittest.navigation.station.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import personal.ivan.corotineretrofittest.R
import personal.ivan.corotineretrofittest.navigation.station.model.MainVhBindingModel
import personal.ivan.corotineretrofittest.navigation.station.viewmodel.StationViewModel

class StationListAdapter(private val mViewModel: StationViewModel) :
    RecyclerView.Adapter<StationViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) =
        StationViewHolder(
            mView =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.vh_main, parent, false)
        )

    override fun onBindViewHolder(
        holder: StationViewHolder,
        position: Int
    ) {
        mViewModel.mainVhBindingModelList.value
            ?.getOrNull(position)
            ?.also { holder.setUp(data = it) }
    }

    override fun getItemCount(): Int =
        mViewModel.mainVhBindingModelList.value?.size ?: 0
}

class StationViewHolder(private val mView: View) : RecyclerView.ViewHolder(mView) {

    /**
     * Set up data to view
     */
    fun setUp(data: MainVhBindingModel) {
        mView.apply {
            findViewById<MaterialTextView>(R.id.textViewStationName).text = data.name
            findViewById<MaterialTextView>(R.id.textViewStationArea).text = data.area
            findViewById<ImageView>(R.id.imageViewWarning).visibility =
                if (data.closed) View.INVISIBLE else View.GONE
            findViewById<MaterialTextView>(R.id.textViewAvailableForRental).text =
                data.availableNumber
            findViewById<MaterialTextView>(R.id.textViewAvailableForReturn).text =
                data.spaceForReturn
        }
    }
}