package com.hossam.forecast.data.network.response

import com.google.gson.annotations.SerializedName
import com.hossam.forecast.data.db.entity.CurrentWeatherEntry
import com.hossam.forecast.data.db.entity.Location

data class CurrentWeatherResponse(
    val location: Location,
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry
)