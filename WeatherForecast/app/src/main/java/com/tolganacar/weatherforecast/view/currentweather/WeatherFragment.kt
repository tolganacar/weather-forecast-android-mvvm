package com.tolganacar.weatherforecast.view.currentweather

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tolganacar.weatherforecast.R
import com.tolganacar.weatherforecast.databinding.FragmentCurrentWeatherBinding
import com.tolganacar.weatherforecast.util.SharedPrefManager
import com.tolganacar.weatherforecast.util.CityConsts.CITY_HOLDER
import com.tolganacar.weatherforecast.util.CityConsts.CITY_NAME
import com.tolganacar.weatherforecast.util.CityConsts.ISTANBUL
import com.tolganacar.weatherforecast.util.setVisible
import com.tolganacar.weatherforecast.view.threedaysweather.ThreeDaysWeatherAdapter
import com.tolganacar.weatherforecast.view.threehourlyweather.ThreeHourlyWeatherAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_current_weather.*

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModels()

    private lateinit var dataBinding: FragmentCurrentWeatherBinding
    private val threeHourlyAdapter = ThreeHourlyWeatherAdapter(arrayListOf())
    private val threeDaysWeatherAdapter = ThreeDaysWeatherAdapter(arrayListOf())

    private lateinit var getSharedPreferences: SharedPreferences
    private lateinit var sharedPrefManager: SharedPrefManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate<FragmentCurrentWeatherBinding?>(
            inflater,
            R.layout.fragment_current_weather,
            container,
            false
        ).apply {
            lifecycleOwner = this@WeatherFragment
            viewModel = this@WeatherFragment.viewModel
        }

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRecyclerview()
        initSharedPreferences()
        observeLiveData()
        setSwipeRefreshLayout()
        searchImageOnClickListener()
        setWeatherListArray()

        viewModel.getAllWeatherInformationFromAPI(sharedPrefManager.getString(CITY_NAME, ISTANBUL))
    }

    private fun initSharedPreferences() {
        getSharedPreferences =
            requireActivity().getSharedPreferences(CITY_HOLDER, Context.MODE_PRIVATE)
        sharedPrefManager = SharedPrefManager(getSharedPreferences)
    }

    private fun initializeRecyclerview() {
        threeHourlyRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = threeHourlyAdapter
        }

        threeDaysRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = threeDaysWeatherAdapter
        }
    }

    private fun observeLiveData() {
        viewModel.currentWeatherUIModel.observe(viewLifecycleOwner, Observer { currentWeather ->
            currentWeather?.let {
                mainPage.setBackgroundResource(it.currentWeatherThemeUI.backgroundResID)
                threeHourlyCardView.setCardBackgroundColor(Color.parseColor(it.currentWeatherThemeUI.cardViewBackgroundColorName))
                threeDaysWeatherCardView.setCardBackgroundColor(Color.parseColor(it.currentWeatherThemeUI.cardViewBackgroundColorName))
            }
        })

        viewModel.threeDaysWeatherUIModel.observe(viewLifecycleOwner, Observer { threeDaysWeather ->
                threeDaysWeatherAdapter.updateTenDayWeatherList(threeDaysWeather.threeDaysWeatherList)
        })

        viewModel.threeHourlyWeatherUIModel.observe(viewLifecycleOwner, Observer { threeHourlyWeather ->
                threeHourlyAdapter.updateThreeHourlyWeatherList(threeHourlyWeather.hourlyWeatherList)
        })

        viewModel.shouldShowErrorMessage.observe(viewLifecycleOwner, Observer { error ->
            errorText.setVisible(error)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { loading ->
            loadingBar.setVisible(loading)
        })
    }

    private fun setSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener {
            errorText.visibility = View.GONE
            loadingBar.visibility = View.VISIBLE
            swipeRefreshLayout.isRefreshing = false

            viewModel.getAllWeatherInformationFromAPI(sharedPrefManager.getString(CITY_NAME, ISTANBUL))
        }
    }

    private fun searchImageOnClickListener() {
        searchImage.setOnClickListener {
            if (resources.getStringArray(R.array.turkey_city).contains(autoCompleteTextView.text.toString())) {
                val cityName = autoCompleteTextView.text.toString()
                sharedPrefManager.putString(CITY_NAME, cityName)
                viewModel.getAllWeatherInformationFromAPI(cityName)
            }
            autoCompleteTextView.setText("")
        }
    }

    private fun setWeatherListArray() {
        val itemArray = resources.getStringArray(R.array.turkey_city)
        val arrayAdapter = context?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, itemArray) }
        autoCompleteTextView.setAdapter(arrayAdapter)
    }
}
