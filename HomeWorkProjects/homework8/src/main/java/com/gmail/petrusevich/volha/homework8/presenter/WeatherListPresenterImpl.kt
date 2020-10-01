package com.gmail.petrusevich.volha.homework8.presenter

import com.gmail.petrusevich.volha.homework8.fragmentsweather.WeatherListView
import com.gmail.petrusevich.volha.homework8.repository.WeatherDataModel
import com.gmail.petrusevich.volha.homework8.repository.WeatherRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class WeatherListPresenterImpl(
        private val weatherRepositoryImpl: WeatherRepositoryImpl,
        private var weatherListView: WeatherListView?,
        private val weatherListItemModelMapper: (List<WeatherDataModel>) -> List<WeatherListItemModel>
) : WeatherListPresenter {

    private var disposable: Disposable? = null


    override fun fetchWeatherList() {
        disposable = weatherRepositoryImpl.getHourlyWeatherList("Minsk")
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