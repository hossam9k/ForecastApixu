package com.hossam.forecast.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hossam.forecast.data.db.entity.CURRENT_WEATHER_ID
import com.hossam.forecast.data.db.entity.CurrentWeatherEntry
import com.hossam.forecast.data.db.unitlocalized.ImperialCurrentWeatherEntry
import com.hossam.forecast.data.db.unitlocalized.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {
    //upsert insert or update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query("SELECT * FROM current_weather WHERE id = $CURRENT_WEATHER_ID")
    fun getWeatherMetric():LiveData<MetricCurrentWeatherEntry>

    @Query("SELECT * FROM current_weather WHERE id = $CURRENT_WEATHER_ID")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEntry>

}





















