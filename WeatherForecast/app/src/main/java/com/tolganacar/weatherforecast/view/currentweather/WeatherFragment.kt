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
    private lateinit var setSharedPreferences: SharedPreferences.Editor
    private var cName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_current_weather,
            container,
            false
        )
        getSharedPreferences =
            requireActivity().getSharedPreferences("CityHolder", Context.MODE_PRIVATE)
        cName = getSharedPreferences.getString("cityName", "istanbul")
        setSharedPreferences = getSharedPreferences.edit()

        dataBinding.lifecycleOwner = this
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeUI()
        initializeRecyclerview()
        observeLiveData()
        setSwipeRefreshLayout()
        searchImageOnClickListener()
        setWeatherListArray()

        viewModel.getAllWeatherInformationFromAPI(cName!!)
    }

    private fun initializeUI() {
        dataBinding.viewModel = viewModel
    }

    private fun initializeRecyclerview() {
        threeHourlyRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        threeHourlyRecyclerView.adapter = threeHourlyAdapter

        threeDaysRecyclerView.layoutManager = LinearLayoutManager(context)
        threeDaysRecyclerView.adapter = threeDaysWeatherAdapter
    }

    private fun observeLiveData() {
        viewModel.currentWeatherUIModel.observe(viewLifecycleOwner, Observer { currentWeather ->
            currentWeather?.let {
                when (it.weatherMain) {
                    "Thunderstorm" -> {
                        mainPage.setBackgroundResource(R.drawable.thunderstorm)
                        threeHourlyCardView.setCardBackgroundColor(Color.parseColor("#EB8C66E1"))
                        threeDaysWeatherCardView.setCardBackgroundColor(Color.parseColor("#EB8C66E1"))
                    }
                    "Drizzle" -> {
                        mainPage.setBackgroundResource(R.drawable.rainy)
                        threeHourlyCardView.setCardBackgroundColor(Color.parseColor("#EB0A3B40"))
                        threeDaysWeatherCardView.setCardBackgroundColor(Color.parseColor("#EB0A3B40"))
                    }
                    "Rain" -> {
                        mainPage.setBackgroundResource(R.drawable.rainy)
                        threeHourlyCardView.setCardBackgroundColor(Color.parseColor("#EB0A3B40"))
                        threeDaysWeatherCardView.setCardBackgroundColor(Color.parseColor("#EB0A3B40"))
                    }
                    "Snow" -> {
                        mainPage.setBackgroundResource(R.drawable.snowy)
                        threeHourlyCardView.setCardBackgroundColor(Color.parseColor("#F2171C83"))
                        threeDaysWeatherCardView.setCardBackgroundColor(Color.parseColor("#F2171C83"))
                    }
                    "Clear" -> {
                        mainPage.setBackgroundResource(R.drawable.clearsky)
                        threeHourlyCardView.setCardBackgroundColor(Color.parseColor("#EB6C9CEC"))
                        threeDaysWeatherCardView.setCardBackgroundColor(Color.parseColor("#EB6C9CEC"))
                    }
                    "Clouds" -> {
                        mainPage.setBackgroundResource(R.drawable.cloudy)
                        threeHourlyCardView.setCardBackgroundColor(Color.parseColor("#EB7FB3D3"))
                        threeDaysWeatherCardView.setCardBackgroundColor(Color.parseColor("#EB7FB3D3"))
                    }
                    else -> {
                        mainPage.setBackgroundColor(Color.parseColor("#57BAE6"))
                        threeHourlyCardView.setCardBackgroundColor(Color.WHITE)
                        threeDaysWeatherCardView.setCardBackgroundColor(Color.WHITE)
                    }
                }
            }
        })

        viewModel.threeDaysWeatherUIModel.observe(viewLifecycleOwner, Observer { threeDaysWeather ->
            threeDaysWeather?.let {
                threeDaysRecyclerView.visibility = View.VISIBLE
                threeDaysWeatherAdapter.updateTenDayWeatherList(threeDaysWeather)
            }
        })

        viewModel.threeHourlyWeatherUIModel.observe(
            viewLifecycleOwner,
            Observer { threeHourlyWeather ->
                threeHourlyWeather?.let {
                    threeHourlyRecyclerView.visibility = View.VISIBLE
                    threeHourlyAdapter.updateThreeHourlyWeatherList(threeHourlyWeather)
                }
            })

        viewModel.shouldShowErrorMessage.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    errorText.visibility = View.VISIBLE
                } else {
                    errorText.visibility = View.GONE
                }
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    loadingBar.visibility = View.VISIBLE
                    errorText.visibility = View.GONE
                } else {
                    loadingBar.visibility = View.GONE
                }
            }
        })
    }

    private fun setSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener {
            errorText.visibility = View.GONE
            loadingBar.visibility = View.VISIBLE
            var cityName = getSharedPreferences.getString("cityName", cName)
            viewModel.getAllWeatherInformationFromAPI(cityName!!)
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun searchImageOnClickListener() {
        searchImage.setOnClickListener {
            if (autoCompleteTextView.text.toString() == "") {
            } else if (!(resources.getStringArray(R.array.turkey_city)
                    .contains(autoCompleteTextView.text.toString()))
            ) {
                autoCompleteTextView.setText("")
            } else {
                val cityName = autoCompleteTextView.text.toString()
                setSharedPreferences.putString("cityName", cityName).apply()
                viewModel.getAllWeatherInformationFromAPI(cityName)
                autoCompleteTextView.setText("")
            }
        }
    }

    private fun setWeatherListArray() {
        val itemArray = resources.getStringArray(R.array.turkey_city)
        val arrayAdapter =
            context?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, itemArray) }
        autoCompleteTextView.setAdapter(arrayAdapter)
    }
}
