package com.example.myapplication

import android.content.SharedPreferences

interface ModelInterface {

    interface Model {

        fun storeBmi(bmi: String)

        fun getBMIArray(onSuccess: (data: String) -> Unit)

        fun setSharpreference(sharedPreferences: SharedPreferences)

        fun resetArraylist(arrayList: ArrayList<String>)
    }
}