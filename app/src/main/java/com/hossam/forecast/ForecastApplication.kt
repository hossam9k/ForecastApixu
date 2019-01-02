package com.hossam.forecast

import android.app.Application
import com.hossam.forecast.data.ApixuWeatherApiService
import com.hossam.forecast.data.db.ForecastDatabase
import com.hossam.forecast.data.network.ConnectivityInterceptor
import com.hossam.forecast.data.network.ConnectivityInterceptorImpl
import com.hossam.forecast.data.network.WeatherNetworkDataSource
import com.hossam.forecast.data.network.WeatherNetworkDataSourceImpl
import com.hossam.forecast.data.repository.ForecastRepository
import com.hossam.forecast.data.repository.ForecastRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class ForecastApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApixuWeatherApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(),instance()) }
    }
}