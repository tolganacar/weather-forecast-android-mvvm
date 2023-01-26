package com.tolganacar.weatherforecast.data.model.currentweather

data class ThreeHourlyWeatherResponseModel(
    val city: City? = null,
    val cnt: Int? = null,
    val cod: String? = null,
    val list: List<ListElements>? = null,
    val message: Int? = null
)

fun ThreeHourlyWeatherResponseModel.getTemperatureText(): String {
    val temperature = list?.get(0)?.main?.temp ?: 0.0
    val degreeSymbol = "\u00B0"

    return "$temperature$degreeSymbol"
}

fun ThreeHourlyWeatherResponseModel.getTime(): String {
    val hour = list?.get(0)?.dt_txt?.substring(10,15)

    return "$hour"
}

fun ThreeHourlyWeatherResponseModel.getIcon(): String {
    val icon = list?.get(0)?.weather?.firstOrNull()?.icon ?: "Unknown"

    return "$icon"
}

data class SysThreeHourly(
    val pod: String
)

data class RainThreeHourly(
    val `3h`: Double
)

data class MainThreeHourly(
    val feels_like: Double,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Double,
    val temp_kf: Double,
    val temp_max: Double,
    val temp_min: Double
)

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)

data class ListElements(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: MainThreeHourly,
    val pop: Double,
    val rain: RainThreeHourly,
    val sys: SysThreeHourly,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)