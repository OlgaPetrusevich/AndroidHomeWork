package com.gmail.petrusevich.volha.project.presentation.exerciselist

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.petrusevich.volha.project.domain.ExerciseDomainModel
import com.gmail.petrusevich.volha.project.domain.ExerciseListUseCase
import com.gmail.petrusevich.volha.project.domain.ExerciseListUseCaseImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ExerciseViewModel(context: Application) : AndroidViewModel(context) {

    private val exercisesViewModelMapper: (List<ExerciseDomainModel>) -> List<ExerciseItemModel> = ExerciseItemModelMapper()
    private val exerciseListUseCase: ExerciseListUseCase = ExerciseListUseCaseImpl(context)
    private var disposable: Disposable? = null

    private val mutableExercisesLiveData = MutableLiveData<List<ExerciseItemModel>>()
    val exercisesLiveData: LiveData<List<ExerciseItemModel>> = mutableExercisesLiveData

    private val mutableExercisesErrorLiveData = MutableLiveData<Throwable>()
    val exercisesErrorLiveData: LiveData<Throwable> = mutableExercisesErrorLiveData


    fun getAllExercises() {
        disposable = exerciseListUseCase.getExerciseList()
                .subscribeOn(Schedulers.computation())
                .map { domainModelList -> exercisesViewModelMapper(domainModelList) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { itemList -> mutableExercisesLiveData.value = itemList },
                        { throwable -> mutableExercisesErrorLiveData.value = throwable }
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