package com.example.myapplication
import kotlin.collections.ArrayList


data class BMIArrayBrean(
        var BMIArray : ArrayList<BMIBean>
)

data class BMIBean(
        var Height : String ,
        var Weight : String ,
        var BMI : String
)


