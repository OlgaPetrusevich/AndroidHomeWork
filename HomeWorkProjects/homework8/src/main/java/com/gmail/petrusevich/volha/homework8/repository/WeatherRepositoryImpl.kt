package com.gmail.petrusevich.volha.homework8.repository

import android.content.Context
import com.gmail.petrusevich.volha.homework8.Settings
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody

private const val API_KEY = "b77fe0d92cd8dbddc0e12ab3753c5cd0"

private const val CELSIUS = "metric"
private const val FAHRENHEIT = "imperial"
class WeatherRepositoryImpl(
        private val okHttpClient: OkHttpClient,
        private val weatherDataModelMapper: (String) -> List<WeatherDataModel>
) : WeatherRepository {

//    private val settings: Settings by lazy { Settings.getInstance() }
    private var units: String = CELSIUS

    override fun getHourlyWeatherList(city: String): Single<List<WeatherDataModel>> {
//        setDegree()
        val url = "http://api.openweathermap.org/data/2.5/forecast?q=$city&cnt=24&units=$units&appid=$API_KEY"
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

//    private fun setDegree(){
//        settings.getSharedPreferences(context!!)
//        units = if(settings.loadSettings()){
//            CELSIUS
//        } else {
//            FAHRENHEIT
//        }

//    }



}