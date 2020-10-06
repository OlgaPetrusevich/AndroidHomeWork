package com.gmail.petrusevich.volha.project

import android.util.Log
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody


class ExerciseRepositoryImpl(
        private val okHttpClient: OkHttpClient,
        private val exerciseDataMapper: (String) -> List<ExerciseDataModel>
) : ExerciseRepository {


    override fun getExerciseList(language: String): Single<List<ExerciseDataModel>> {
        val url = "https://wger.de/api/v2/exercise/?muscles=1&equipment=3&status=2&appid=9e8eb773937ae1dfcead279d69f75c3f9f5f524e"
        val request = Request.Builder().url(url).build()
        return Single.create<String> { emitter ->
            okHttpClient.newCall(request).execute().use { response ->
                Log.d("exer", response.code.toString())
                if (!response.isSuccessful) emitter.onError(Throwable("Server error code: ${response.code}"))
                if (response.body == null) emitter.onError(NullPointerException("Body is null"))
                emitter.onSuccess((response.body as ResponseBody).string())
            }
        }.subscribeOn(Schedulers.io())
                .map { jsonData ->
                    exerciseDataMapper(jsonData)
                }
    }


}