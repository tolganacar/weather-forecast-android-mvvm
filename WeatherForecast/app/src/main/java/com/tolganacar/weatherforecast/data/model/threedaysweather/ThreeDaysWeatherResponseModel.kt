package com.tolganacar.weatherforecast.data.model.threedaysweather

data class ThreeDaysWeatherResponseModel(
    val forecast: Forecast? = null,
)

data class Condition(
    val icon: String,
)

data class Day(
    val condition: Condition,
    val maxtemp_c: Double,
    val mintemp_c: Double,
)

data class Forecast(
    val forecastday: List<Forecastday>
)

data class Forecastday(
    val date: String,
    val day: Day,
)

fun Forecastday.getDateText(): String {
    return date
}

fun Forecastday.getImageUrl(): String {
    return "https://" + day.condition.icon
}

fun Forecastday.getMinTemperatureText(): String {
    val degreeSymbol = "\u00B0"
    return day.mintemp_c.toString() + degreeSymbol
}

fun Forecastday.getMaxTemperatureText(): String {
    val degreeSymbol = "\u00B0"
    return day.maxtemp_c.toString() + degreeSymbol
}