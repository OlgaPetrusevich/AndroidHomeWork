package com.gmail.petrusevich.volha.project.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmail.petrusevich.volha.project.data.HistoryExerciseDataModel
import com.gmail.petrusevich.volha.project.domain.HistoryExerciseDomainModel
import com.gmail.petrusevich.volha.project.domain.HistoryListUseCase
import com.gmail.petrusevich.volha.project.domain.HistoryListUseCaseImpl
import com.gmail.petrusevich.volha.project.presentation.exerciselist.itemmodel.HistoryExerciseItemModel
import com.gmail.petrusevich.volha.project.presentation.exerciselist.itemmodel.HistoryItemModelMapper
import com.gmail.petrusevich.volha.project.presentation.historylist.DateTypeMapper
import com.prolificinteractive.materialcalendarview.CalendarDay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HistoryExercisesViewModel(context: Application) : AndroidViewModel(context) {

    private val historyItemModelMapper: (List<HistoryExerciseDomainModel>) -> List<HistoryExerciseItemModel> = HistoryItemModelMapper()
    private val dateTypeMapper: (List<String>) -> List<CalendarDay> = DateTypeMapper()
    private val historyListUseCase: HistoryListUseCase = HistoryListUseCaseImpl(context)
    private var disposable: Disposable? = null

    private val mutableHistoryLiveData = MutableLiveData<List<HistoryExerciseItemModel>>()
    val historyLiveData: LiveData<List<HistoryExerciseItemModel>> = mutableHistoryLiveData

    private val mutableHistoryErrorLiveData = MutableLiveData<Throwable>()
    val historyErrorLiveData: LiveData<Throwable> = mutableHistoryErrorLiveData

    private val mutableDateLiveData = MutableLiveData<List<CalendarDay>>()
    val dateLiveData: LiveData<List<CalendarDay>> = mutableDateLiveData

    fun getDateHistory(date: String) {
        disposable = historyListUseCase.getDateHistory(date)
                .subscribeOn(Schedulers.computation())
                .map { domainModelList -> historyItemModelMapper(domainModelList) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { itemList -> mutableHistoryLiveData.value = itemList },
                        { throwable -> mutableHistoryErrorLiveData.value = throwable }
                )
    }

    fun getCategoryHistory(categoryName: String) {
        disposable = historyListUseCase.getCategoryHistory(categoryName)
                .subscribeOn(Schedulers.computation())
                .map { domainModelList -> historyItemModelMapper(domainModelList) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { itemList -> mutableHistoryLiveData.value = itemList },
                        { throwable -> mutableHistoryErrorLiveData.value = throwable }
                )
    }

    fun insertExerciseToHistory(historyExerciseData: HistoryExerciseDataModel) {
        historyListUseCase.insertExerciseToHistory(historyExerciseData)
    }

    fun getAllDate() {
        disposable = historyListUseCase.getAllDate()
                .subscribeOn(Schedulers.computation())
                .map { listString -> dateTypeMapper(listString) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { itemList -> mutableDateLiveData.value = itemList },
                        { throwable -> mutableHistoryErrorLiveData.value = throwable }
                )
    }


    override fun onCleared() {
        super.onCleared()
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

}