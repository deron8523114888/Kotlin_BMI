package com.example.myapplication

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import java.math.BigDecimal

class Presenter(viewInterface: BMIContract.View) : BMIContract.Presenter {

    private val mViewInterface = viewInterface

    private val mModel = Model()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun calculate_store_bmi(height: String, weight: String): Boolean {

        // 檢查是否空字串
        if (height.isEmpty() || weight.isEmpty()) {
            mViewInterface.dialog("失敗", "身高或體重為空", "了解")
            return false
        }
        // 將使用者輸入的 字串 轉為 Double
        val heightValue = height.toDouble()
        val weightValue = weight.toDouble()

        // 計算bmi
        val bmi = weightValue / Math.pow(heightValue, 2.0)

        // 將 bmi 以四捨五入取至小數點後第二位
        val bmi2Decimal = android.icu.math.BigDecimal(bmi).setScale(2, BigDecimal.ROUND_HALF_UP).toString()

        // 叫 Model 存計算好的數據
        mModel.storeBmi(bmi2Decimal)
        mViewInterface.dialog("成功", "請於下方查看BMI", "了解")

        return true
    }

    override fun get_bmi() {  //從 model 拿 bmiArray 給 View
        mModel.getBMIArray { data ->

            if (!data.isEmpty()) {
                // 將從 Model 取得的 String 轉為 Arraylit
                val str = data.substring(1, data.length - 1)
                val arrayList = ArrayList<String>(str.split(", "))

                // 更新 Model 暫存的 Arraylist
                mModel.resetArraylist(arrayList)

                // 將 BMI 資料交給 View
                mViewInterface.show_bmi(arrayList)
            }
        }
    }

    override fun setSharpreference(sharedPreferences: SharedPreferences) {
        mModel.setSharpreference(sharedPreferences)
    }
}