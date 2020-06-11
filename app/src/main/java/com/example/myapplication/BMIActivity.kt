package com.example.myapplication

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import com.zhy.baseadapter_recyclerview.BuildConfig
import kotlinx.android.synthetic.main.activity_bmi.*

class BMIActivity : AppCompatActivity(), BMIContract.View {

    var presenter: Presenter? = null
    val context = this




    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)



        init()
        click()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun init() {

        // 初始化 Presenter 對象
        presenter = Presenter(this)

        // 初始化 SharePreference "BMIData" 倉庫
        SingletonSharePreference.getShare(context)

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
            presenter?.calculate_store_bmi(et_height.text.toString(), et_weight.text.toString())

            // 若計算成功，取得 bmi 值
            presenter?.get_bmi()    // Todo 輸入錯誤的身高體重也會執行 需fix

        })

        // 重設身高、體重按鈕
        bt_reset.setOnClickListener(View.OnClickListener {
            et_height.text.clear()
            et_weight.text.clear()
        })
    }

    //顯示 bmi ，每次按下計算按鈕，計算成功後會執行
    override fun show_bmi(bmi_array: ArrayList<BMIBean>) {

        val adapter = CommonAdaper(this,bmi_array)
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

    override fun setSharePreference(func: (sharepreference: SharedPreferences) -> Unit) {
        val sharedPreferences = getSharedPreferences("BMIData", Context.MODE_PRIVATE)
        func(sharedPreferences)
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