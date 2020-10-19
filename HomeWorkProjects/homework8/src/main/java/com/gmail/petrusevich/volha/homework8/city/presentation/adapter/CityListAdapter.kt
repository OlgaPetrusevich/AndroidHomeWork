package com.gmail.petrusevich.volha.homework8.city.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.petrusevich.volha.homework8.R
import com.gmail.petrusevich.volha.homework8.city.presentation.itemmodel.CityItemModel
import kotlinx.android.synthetic.main.item_city_list.view.*

class CityListAdapter(
        private val itemOnClickListener: ItemOnClickListener
) : RecyclerView.Adapter<CityListAdapter.CityListViewHolder>() {

    private val cityList = mutableListOf<CityItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city_list, parent, false)
        return CityListViewHolder(view, itemOnClickListener)
    }


    override fun getItemCount(): Int = cityList.size

    override fun onBindViewHolder(holder: CityListViewHolder, position: Int) {
        holder.bind(cityList[position])
    }

    fun updateCityList(newCityList: List<CityItemModel>) {
        cityList.apply {
            clear()
            addAll(newCityList)
        }
        notifyDataSetChanged()
    }

    class CityListViewHolder(
            itemView: View, private val itemOnClickListener: ItemOnClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        fun bind(cityItemModel: CityItemModel) {
            with(cityItemModel) {
                itemView.apply {
                    viewCityText.text = cityName
                }
            }
        }

        override fun onClick(item: View?) {
            itemOnClickListener.itemOnClick(adapterPosition)
        }
    }


}