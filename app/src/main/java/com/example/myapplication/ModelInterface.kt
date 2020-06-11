package com.example.myapplication


interface ModelInterface {

    interface Model {

        fun storeBmi(height : String , weight : String , bmi: String)

        fun getBMIArray(onSuccess: (data: ArrayList<BMIBean>) -> Unit)

    }
}