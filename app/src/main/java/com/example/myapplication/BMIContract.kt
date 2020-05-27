package com.example.myapplication

/**
 *  author: Tim Chen
 *  time  : 2020-05-27
 *  desc  :
 */
interface BMIContract {

    interface View {

        fun show_bmi(bmi_array: ArrayList<Double>) {}

        fun dialog(title: String, messenger: String, negativeString: String) {}
    }

    interface Presenter {

        fun calculate_store_bmi(hheight: String, weight: String)   //過濾後的數值，開始計算BMI，並丟給 Model 儲存

        fun get_bmi()
    }

    interface Model {

        fun storeBmi(bmi: Double)

        fun getBMIArray(onSuccess: (datd: ArrayList<Double>) -> Unit)
    }
}