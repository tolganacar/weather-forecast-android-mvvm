package com.tolganacar.weatherforecast.data.model.currentweather

import android.graphics.Color
import com.tolganacar.weatherforecast.R
import com.tolganacar.weatherforecast.util.CityConsts
import com.tolganacar.weatherforecast.util.ColorConsts
import com.tolganacar.weatherforecast.view.currentweather.CurrentWeatherThemeUI

data class CurrentWeatherResponseModel(
    val weather: List<Weather>? = null,
    val main: Main? = null,
    val name: String? = null,
)

data class Weather(
    val main: String,
    val description: String,
    val icon: String
)

data class Main(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double
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

fun CurrentWeatherResponseModel.getWeatherMain(): String {
    return weather?.firstOrNull()?.main ?: "Unknown"
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

fun CurrentWeatherResponseModel.getCurrentWeatherTheme() : CurrentWeatherThemeUI {
    var backgroundID: Int
    var cardViewBackgroundID: String

    when (getWeatherMain()) {
        "Thunderstorm" -> {
            backgroundID = R.drawable.thunderstorm
            cardViewBackgroundID = ColorConsts.THUNDERSTORM_COLOR
        }
        "Drizzle" -> {
            backgroundID = R.drawable.rainy
            cardViewBackgroundID = ColorConsts.RAIN_COLOR
        }
        "Rain" -> {
            backgroundID = R.drawable.rainy
            cardViewBackgroundID = ColorConsts.RAIN_COLOR
        }
        "Snow" -> {
            backgroundID = R.drawable.snowy
            cardViewBackgroundID = ColorConsts.SNOW_COLOR
        }
        "Clear" -> {
            backgroundID = R.drawable.clearsky
            cardViewBackgroundID = ColorConsts.SUNNY_COLOR
        }
        "Clouds" -> {
            backgroundID = R.drawable.cloudy
            cardViewBackgroundID = ColorConsts.CLOUDY_COLOR
        }
        else -> {
            backgroundID = Color.parseColor(ColorConsts.DEFAULT_COLOR)
            cardViewBackgroundID = ColorConsts.WHITE_COLOR
        }

    }
    return CurrentWeatherThemeUI(backgroundID, cardViewBackgroundID)
}