package com.example.myapplication

class Presenter(viewInterface: BMIContract.View) : BMIContract.Presenter {

    private val mViewInterface = viewInterface

    private val mModel = Model()

    override fun calculate_store_bmi(height: String, weight: String) {

        // 檢查是否空字串
        if (height.isEmpty() || weight.isEmpty()) {
            mViewInterface.dialog("失敗", "身高或體重為空", "了解")
            return
        }

        val heightValue = height.toDouble()
        val weightValue = weight.toDouble()

        // 計算bmi
        val bmi = weightValue / Math.pow(heightValue, 2.0)

        // 叫 Model 存計算好的數據
        mModel.storeBmi(bmi)
    }   // 過濾後的數值，開始計算BMI，並丟給 Model 儲存


    override fun get_bmi() {

        mModel.getBMIArray({ data -> })

        mModel.getBMIArray { data ->

            mViewInterface.show_bmi(data)

        }  //從 model 拿 bmiArray 給 View
    }
}