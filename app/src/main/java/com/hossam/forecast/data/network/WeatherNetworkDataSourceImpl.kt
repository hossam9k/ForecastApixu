package com.hossam.forecast.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hossam.forecast.data.ApixuWeatherApiService
import com.hossam.forecast.data.network.response.CurrentWeatherResponse
import com.hossam.forecast.internal.NoConnectivityException

class WeatherNetworkDataSourceImpl(
    private val apixuWeatherApiService: ApixuWeatherApiService
): WeatherNetworkDataSource {

    private val _downloadCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadCurrentWeather

    override suspend fun fetchCurrentWeather(location: String, languageCode: String) {
        try{
            val fetchedCurrentWeather = apixuWeatherApiService
                .getCurrentWeather(location,languageCode)
                .await()
            _downloadCurrentWeather.postValue(fetchedCurrentWeather)
        }

        catch (e: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.",e)
        }
    }
}