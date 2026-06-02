package com.example.calculator

import android.util.Log
import androidx.lifecycle.ViewModel

class CalculatorViewModel  : ViewModel(){


    fun onButtonViewModelClick(btn: String) {
        Log.i("Clicked Button", btn)
    }
}
