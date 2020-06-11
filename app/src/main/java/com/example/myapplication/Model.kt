package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Model (): ModelInterface.Model {

    var arrayList: ArrayList<BMIBean> = ArrayList()


    override fun storeBmi(height : String , weight : String , bmi: String) {

        // 將資料轉為 data
        var data = BMIBean(Height = height,Weight = weight ,BMI = bmi)

        // 存入暫存 Arraylist
        arrayList.add(data)

        // 存入 SharePreference
        SingletonSharePreference.storeBMI(BMIArrayBrean(arrayList))


    }

    override fun getBMIArray(onSuccess: (data: ArrayList<BMIBean>) -> Unit) {

        // 從 SharePreference 取出 BMIArray
        onSuccess(SingletonSharePreference.getBMI().BMIArray)

    }

}