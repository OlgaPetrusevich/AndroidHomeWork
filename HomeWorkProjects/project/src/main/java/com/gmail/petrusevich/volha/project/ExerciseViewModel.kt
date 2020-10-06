package com.gmail.petrusevich.volha.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import okhttp3.OkHttpClient

class ExerciseViewModel : ViewModel() {

    private val exerciseList = MutableLiveData<List<ExerciseDataModel>>()
    val liveDataExerciseList: LiveData<List<ExerciseDataModel>> = exerciseList
    private val exerciseRepositoryImpl = ExerciseRepositoryImpl(OkHttpClient(), ExerciseDataMapper())
    private var disposable: Disposable? = null

    fun fetchExerciseList() {
        disposable = exerciseRepositoryImpl.getExerciseList("2")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {list -> exerciseList.value = list},
                        {trowable -> trowable.toString()}
                )

    }


}