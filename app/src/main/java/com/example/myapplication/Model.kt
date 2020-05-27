package com.example.myapplication

class Model : BMIContract.Model {

    var arrayList: ArrayList<Double> = ArrayList()


    override fun storeBmi(bmi: Double) {
        arrayList.add(bmi)
    }


    override fun getBMIArray(onSuccess: (datd: ArrayList<Double>) -> Unit) {

        // Todo 執行資料存取

        //fhjrukygwiehrv

        // Todo 資料存取成功
        onSuccess(arrayList)


        // Todo 資料存取失敗

    }
}