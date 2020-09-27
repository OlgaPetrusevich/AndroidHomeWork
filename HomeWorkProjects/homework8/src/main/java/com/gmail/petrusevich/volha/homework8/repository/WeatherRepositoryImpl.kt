package com.gmail.petrusevich.volha.homework8.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody

private const val API_KEY = "b77fe0d92cd8dbddc0e12ab3753c5cd0"

class WeatherRepositoryImpl(
        private val okHttpClient: OkHttpClient,
        private val weatherDataModelMapper: (String) -> List<WeatherDataModel>
) : WeatherRepository {


    override fun getHourlyWeatherList(city: String): Single<List<WeatherDataModel>> {
        val url = "http://pro.openweathermap.org/data/2.5/forecast/hourly?q=$city&appid=$API_KEY"
        val request = Request.Builder().url(url).build()
        return Single.create<String> { emitter ->
            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) emitter.onError(Throwable("Server error code: ${response.code}"))
                if (response.body == null) emitter.onError(NullPointerException("Body is null"))
                emitter.onSuccess((response.body as ResponseBody).string())
            }
        }.subscribeOn(Schedulers.io())
                .map { jsonData -> weatherDataModelMapper(jsonData) }
    }
}