package com.gmail.petrusevich.volha.homework8.city.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmail.petrusevich.volha.homework8.city.data.CityDataModel
import com.gmail.petrusevich.volha.homework8.city.domain.CityDomainModel
import com.gmail.petrusevich.volha.homework8.city.domain.CityListUseCase
import com.gmail.petrusevich.volha.homework8.city.domain.CityListUseCaseImpl
import com.gmail.petrusevich.volha.homework8.city.presentation.itemmodel.CityItemModel
import com.gmail.petrusevich.volha.homework8.city.presentation.itemmodel.CityItemModelMapper
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CityListViewModel(
        context: Application) : AndroidViewModel(context) {

    private val cityListItemModelMapper: (List<CityDomainModel>) -> List<CityItemModel> = CityItemModelMapper()
    private val cityListUseCase: CityListUseCase = CityListUseCaseImpl(context)
    private var disposable: Disposable? = null

    private val mutableCityListLiveData = MutableLiveData<List<CityItemModel>>()
    val cityListLiveData: LiveData<List<CityItemModel>> = mutableCityListLiveData

    private val mutableCityErrorLiveData = MutableLiveData<Throwable>()
    val cityErrorLiveData: LiveData<Throwable> = mutableCityErrorLiveData

    fun getCityList() {
        disposable = cityListUseCase.getCityList()
                .subscribeOn(Schedulers.computation())
                .map { domainModelList -> cityListItemModelMapper(domainModelList) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { itemList -> mutableCityListLiveData.value = itemList },
                        { throwable -> mutableCityErrorLiveData.value = throwable }
                )
    }

    fun insertCity(cityDataModel: CityDataModel){
        disposable = Observable.create(ObservableOnSubscribe<CityDataModel>(){
            cityListUseCase.insertCity(cityDataModel)
        }).subscribeOn(Schedulers.computation())
                .subscribe()
    }

}
