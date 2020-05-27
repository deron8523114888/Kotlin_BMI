package com.example.myapplication

class Model : ModelIterface {

    var arrayList: ArrayList<Double> = ArrayList()


    override fun store_bmi(bmi: Double) {
        arrayList.add(bmi)
    }

    fun get_bmiArray(): ArrayList<Double> {
        return arrayList
    }
}