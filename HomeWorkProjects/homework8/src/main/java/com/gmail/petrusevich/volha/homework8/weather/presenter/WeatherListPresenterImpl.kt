package com.gmail.petrusevich.volha.homework8.weather.presenter

import com.gmail.petrusevich.volha.homework8.weather.fragmentsweather.WeatherListView
import com.gmail.petrusevich.volha.homework8.weather.repository.WeatherDataModel
import com.gmail.petrusevich.volha.homework8.weather.repository.WeatherRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class WeatherListPresenterImpl(
        private val weatherRepositoryImpl: WeatherRepositoryImpl,
        private var weatherListView: WeatherListView?,
        private val weatherListItemModelMapper: (List<WeatherDataModel>) -> List<WeatherListItemModel>
) : WeatherListPresenter {

    private var disposable: Disposable? = null


    override fun fetchWeatherList(units: String, city: String) {
        disposable = weatherRepositoryImpl.getHourlyWeatherList(units, city)
                .map { weatherListItemModelMapper(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { list -> weatherListView?.showWeatherList(list) },
                        { throwable -> weatherListView?.onError(throwable.toString()) }
                )
    }

    override fun dispose() {
        disposable?.dispose()
        weatherListView = null
    }

}