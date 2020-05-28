package com.example.myapplication

import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_bmi.*

class BMI_Activity : AppCompatActivity(), BMIContract.View {

    var presenter: Presenter? = null


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        init()
        click()
    }

    private fun init() {

        // 初始化 Presenter 對象
        presenter = Presenter(this)

        // 取得 sharedPreference 裡面 BMIData 檔案對象
        val sharedPreferences = getSharedPreferences("BMIData", Context.MODE_PRIVATE)

        // 將 sharePreference 丟入 Presenter
        presenter?.setSharpreference(sharedPreferences)

        // 設置 rv 的 linearLayoutManager
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_bmishow.layoutManager = linearLayoutManager

        // 取得資料庫裡的紀錄
        presenter?.get_bmi()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun click() {

        // 計算 BMI 按鈕
        bt_calculate.setOnClickListener(View.OnClickListener {
            // 將用者輸入字串丟入 Presenter 回傳布林值 -> 計算成功與否
            val boolean = presenter?.calculate_store_bmi(et_height.text.toString(), et_weight.text.toString())

            // 若計算成功，取得 bmi 值
            if (boolean!!) {
                presenter?.get_bmi()
            }
        })

        // 重設身高、體重按鈕
        bt_reset.setOnClickListener(View.OnClickListener {
            et_height.text.clear()
            et_weight.text.clear()
        })
    }

    //顯示 bmi ，每次按下計算按鈕，計算成功後會執行
    override fun show_bmi(bmi_array: ArrayList<String>) {

        val adapter = TestAdaper(bmi_array)
        rv_bmishow.adapter = adapter
        super.show_bmi(bmi_array)
    }

    override fun dialog(title: String, messenger: String, negativeString: String) {
        val dialog = AlertDialog.Builder(this)

        dialog.run {
            setTitle(title)
            setMessage(messenger)
            setCancelable(false)
            setNegativeButton(negativeString, DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
            show()
        }

        super.dialog(title, messenger, negativeString)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (currentFocus != null && currentFocus!!.windowToken != null) {
                assert(manager != null)
                manager.hideSoftInputFromWindow(currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        return super.onTouchEvent(event)
    }
}