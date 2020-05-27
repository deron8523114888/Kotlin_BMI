package com.example.myapplication

class Presenter(viewIterface: ViewIterface) {

    val mViewIterface = viewIterface

    val mModel = Model()

    fun calculate_store_bmi(height: Double, weight: Double) {
        var bmi = weight / Math.pow(height, 2.0)  //計算bmi
        mModel.store_bmi(bmi)               //叫 Model 存計算好的數據
    }   //過濾後的數值，開始計算BMI，並丟給 Model 儲存

    fun detect_number(height: String, weight: String) {   //偵測使用者輸入的字串是否符合規定
        try {
            if (height.length == 0 || weight.length == 0) {
                mViewIterface.dialog("失敗", "身高或體重為空", "了解")
            } else {
                calculate_store_bmi(height.toDouble(), weight.toDouble())
                mViewIterface.dialog("成功", "請於下方查看BMI", "了解")
            }
        } catch (N: NumberFormatException) {
            mViewIterface.dialog("失敗", "請輸入正確的數值", "了解")
        }
    }   //偵測 view 丟進來的身高體重，若沒問題則開始計算

    fun get_bmi() {
        mViewIterface.show_bmi(mModel.get_bmiArray())  //從 model 拿 bmiArray 給 View
    }
}