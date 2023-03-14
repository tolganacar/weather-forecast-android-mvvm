package com.tolganacar.weatherforecast.util

import android.view.View

fun View.setVisible(isVisible: Boolean?) {
    if (isVisible == true){
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}