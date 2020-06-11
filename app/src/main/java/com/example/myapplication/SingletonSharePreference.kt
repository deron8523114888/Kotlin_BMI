package com.example.myapplication

import android.content.Context
import com.google.gson.Gson

object SingletonSharePreference {

    var context: Context? = null

    fun getShare(context: Context) {
        this.context = context
    }

    fun storeBMI(bmiArrayBrean: BMIArrayBrean) {
        var jsonBmi = Gson().toJson(bmiArrayBrean)
        context?.getSharedPreferences("BMIData", Context.MODE_PRIVATE)?.edit()?.putString("BMIData", jsonBmi)?.apply()
    }

    fun getBMI(): BMIArrayBrean {
        var getstring = context?.getSharedPreferences("BMIData", Context.MODE_PRIVATE)?.getString("BMIData", "")
        return Gson().fromJson(getstring,BMIArrayBrean::class.java)
    }
}