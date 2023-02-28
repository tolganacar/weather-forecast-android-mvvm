package com.tolganacar.weatherforecast.data.model.currentweather

data class CurrentWeatherResponseModel(
    val coord: Coord? = null,
    val weather: List<Weather>? = null,
    val base: String? = null,
    val main: Main? = null,
    val visibility: Int? = null,
    val wind: Wind? = null,
    val rain: Rain? = null,
    val clouds: Clouds? = null,
    val dt: Int? = null,
    val sys: Sys? = null,
    val timezone: Int? = null,
    val id: Int? = null,
    val name: String? = null,
    val cod: Int? = null
)

fun CurrentWeatherResponseModel.getTemperatureText(): String {
    val temperature = main?.temp.toString().substring(0,3)
    val degreeSymbol = "\u00B0"

    return "$temperature$degreeSymbol"
}

fun CurrentWeatherResponseModel.getWeatherStatus(): String {
    return weather?.firstOrNull()?.description ?: "Unknown"
}

fun CurrentWeatherResponseModel.getMinTemperatureText(): String {
    val minTemperature = main?.temp_min.toString().substring(0,3)
    val degreeSymbol = "\u00B0"

    return "L:$minTemperature$degreeSymbol"
}

fun CurrentWeatherResponseModel.getMaxTemperatureText(): String {
    val maxTemperature = main?.temp_max.toString().substring(0,3)
    val degreeSymbol = "\u00B0"

    return "H:$maxTemperature$degreeSymbol"
}

data class Coord(
    val lon: Double,
    val lat: Double
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int,
    val sea_level: Int,
    val grnd_level: Int
)

data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double
)

data class Rain(
    val `1h`: Double
)

data class Clouds(
    val all: Int
)

data class Sys(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Int,
    val sunset: Int
)