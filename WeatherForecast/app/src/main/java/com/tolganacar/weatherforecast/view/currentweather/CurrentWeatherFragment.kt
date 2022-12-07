package com.tolganacar.weatherforecast.view.currentweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tolganacar.weatherforecast.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentWeatherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_weather, container, false)
    }

}