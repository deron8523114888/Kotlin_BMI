package com.example.myapplication

interface ViewIterface {

    fun show_bmi(bmi_array: ArrayList<Double>) {}

    fun dialog(title: String, messenger: String, negativeString: String) {}
}