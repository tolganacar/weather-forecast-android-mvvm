package com.tolganacar.weatherforecast.data.model.threehourlyweather

import com.tolganacar.weatherforecast.data.model.currentweather.Weather

data class ThreeHourlyWeatherResponseModel(
    val list: List<ListElements>? = null,
)

fun ListElements.getTemperatureText(): String {

    val degreeSymbol = "\u00B0"
    lateinit var getTemperature: String

    if (main.temp.toString().length == 5) {
        var temperature = main.temp.toString().substring(0, 4)

        if (temperature.endsWith(".")){
            temperature += "0"
        }

        getTemperature = "$temperature$degreeSymbol"
    } else if (main.temp.toString().length == 3 || main.temp.toString().length == 4) {
        var temperature = main.temp.toString().substring(0, 3)

        if (temperature.endsWith(".")){
            temperature += "0"
        }

        getTemperature = "$temperature$degreeSymbol"
    }

    return getTemperature
}

fun ListElements.getHourText(): String {
    return dt_txt.substring(10,16)
}

fun ListElements.getImageUrl(): String {
    return "http://openweathermap.org/img/w/" + weather[0].icon + ".png"
}

data class MainThreeHourly(
    val temp: Double,
)

data class ListElements(
    val dt_txt: String,
    val main: MainThreeHourly,
    val weather: List<Weather>,
)