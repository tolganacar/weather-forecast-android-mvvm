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
    val degreeSymbol = "\u00B0"
    lateinit var getTemperature: String

    if (main?.temp.toString().length == 5) {
        var temperature = main?.temp.toString().substring(0, 4)

        if (temperature.endsWith(".")){
            temperature += "0"
        }

        getTemperature = "$temperature$degreeSymbol"
    } else if (main?.temp.toString().length == 3 || main?.temp.toString().length == 4) {
        var temperature = main?.temp.toString().substring(0, 3)

        if (temperature.endsWith(".")){
            temperature += "0"
        }

        getTemperature = "$temperature$degreeSymbol"
    }

    return getTemperature
}

fun CurrentWeatherResponseModel.getWeatherStatus(): String {
    return weather?.firstOrNull()?.description ?: "Unknown"
}

fun CurrentWeatherResponseModel.getMinTemperatureText(): String {
    val degreeSymbol = "\u00B0"
    lateinit var getMinTemperature: String

    if (main?.temp_min.toString().length == 5) {
        var minTemperature = main?.temp_min.toString().substring(0, 4)

        if (minTemperature.endsWith(".")){
            minTemperature += "0"
        }

        getMinTemperature = "L:$minTemperature$degreeSymbol"
    } else if (main?.temp_min.toString().length == 3 || main?.temp_min.toString().length == 4) {
        var minTemperature = main?.temp_min.toString().substring(0, 3)

        if (minTemperature.endsWith(".")){
            minTemperature += "0"
        }

        getMinTemperature = "L:$minTemperature$degreeSymbol"
    }

    return getMinTemperature
}

fun CurrentWeatherResponseModel.getMaxTemperatureText(): String {

    val degreeSymbol = "\u00B0"
    lateinit var getMaxTemperature: String

    if (main?.temp_max.toString().length == 5){
        var maxTemperature = main?.temp_max.toString().substring(0, 4)

        if (maxTemperature.endsWith(".")){
            maxTemperature += "0"
        }

        getMaxTemperature = "H:$maxTemperature$degreeSymbol"
    } else if (main?.temp_max.toString().length == 3 || main?.temp_max.toString().length == 4){
        var maxTemperature = main?.temp_max.toString().substring(0, 3)

        if (maxTemperature.endsWith(".")){
            maxTemperature += "0"
        }

        getMaxTemperature = "H:$maxTemperature$degreeSymbol"
    }

    return getMaxTemperature
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