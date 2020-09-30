package com.gmail.petrusevich.volha.homework8.repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gmail.petrusevich.volha.homework8.R
import com.gmail.petrusevich.volha.homework8.presenter.WeatherListItemModel
import kotlinx.android.synthetic.main.item_view.view.*

class WeatherListAdapter : RecyclerView.Adapter<WeatherListAdapter.WeatherListViewHolder>() {

    private val weatherList = mutableListOf<WeatherListItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder =
            WeatherListViewHolder(
                    itemView = parent.run {
                        LayoutInflater.from(context).inflate(R.layout.item_view, this, false)
                    }
            )

    override fun getItemCount(): Int = weatherList.size

    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    fun updateWeatherList(newWeatherList: List<WeatherListItemModel>) {
        weatherList.apply {
            clear()
            addAll(newWeatherList)
        }
        notifyDataSetChanged()
    }

    class WeatherListViewHolder(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(weatherListItemModel: WeatherListItemModel) {
            with(weatherListItemModel) {
                itemView.apply {
                    viewItemTime.text = time
                    viewItemDescription.text = description
                    viewItemDegree.text = temperature
                }
                Glide.with(itemView.context)
                        .load(urlIcon)
                        .into(itemView.viewItemImage)
            }
        }
    }


}