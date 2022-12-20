package com.tolganacar.weatherforecast.view.currentweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tolganacar.weatherforecast.R
import com.tolganacar.weatherforecast.databinding.FragmentCurrentWeatherBinding
import com.tolganacar.weatherforecast.viewmodel.currentweather.CurrentWeatherViewModel
import kotlinx.android.synthetic.main.fragment_current_weather.*

class CurrentWeatherFragment : Fragment() {

    private lateinit var viewModel: CurrentWeatherViewModel
    private lateinit var dataBinding: FragmentCurrentWeatherBinding

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
        dataBinding.lifecycleOwner = this
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViewModel()
        observeLiveData()
        setSwipeRefreshLayout()
        viewModel.getCurrentWeatherFromAPI()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this).get(CurrentWeatherViewModel::class.java)
        dataBinding.viewModel = viewModel
    }

    private fun observeLiveData() {
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
            viewModel.getCurrentWeatherFromAPI()
            swipeRefreshLayout.isRefreshing = false
        }
    }

}