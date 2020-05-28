package com.example.myapplication

import android.content.SharedPreferences
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Model : ModelInterface.Model {

    var arrayList: ArrayList<String> = ArrayList()
    var sharedPreferences: SharedPreferences? = null
    var edit: SharedPreferences.Editor? = null


    override fun storeBmi(bmi: String) {

        val time = SimpleDateFormat("MM-dd HH:mm:ss", Locale.TAIWAN).format(Calendar.getInstance().time)

        // 資料存入暫存
        arrayList.add("【" + time + "】：" + bmi)

        // 資料存入 sharePreeference
        edit?.putString("BMIData", arrayList.toString())?.apply()
    }

    override fun getBMIArray(onSuccess: (data: String) -> Unit) {

        // 取出資料
        val bmiString = sharedPreferences?.getString("BMIData", "").toString()
        onSuccess(bmiString)
    }

    override fun setSharpreference(sharedPreferences: SharedPreferences) {
        this.sharedPreferences = sharedPreferences
        edit = sharedPreferences.edit()
    }

    override fun resetArraylist(arrayList: ArrayList<String>) {
        this.arrayList = arrayList
    }
}