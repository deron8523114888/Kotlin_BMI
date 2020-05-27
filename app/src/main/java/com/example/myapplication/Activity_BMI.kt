package com.example.myapplication

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view.*

class Activity_BMI : AppCompatActivity(), ViewIterface {

    var persenter: Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view)

        init()
        click()
    }


    private fun init() {

        persenter = Presenter(this)

        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_bmishow.layoutManager = linearLayoutManager
    }

    private fun click() {

        bt_calculate.setOnClickListener(View.OnClickListener {
            persenter?.detect_number(et_height.text.toString(), et_weight.text.toString())
            persenter?.get_bmi()
        })


        bt_reset.setOnClickListener(View.OnClickListener {
            et_height.text.clear()
            et_weight.text.clear()
        })
    }


    override fun show_bmi(bmi_array: ArrayList<Double>) {
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


    class TestAdaper(val arrayList: ArrayList<Double>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            Log.v("test_", "onCreateViewHolder")
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bmi_show, null, false)
            val ViewHolder = ViewHolder(view)
            return ViewHolder
        }

        override fun getItemCount(): Int {
            Log.v("test_", "getItemCount")
            return arrayList.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            Log.v("test_", "onBindViewHolder")
            val num = position + 1
            val BMI: TextView = holder.itemView.findViewById(R.id.tv_bmi)
            BMI.setText("第" + num + "次測試：" + arrayList[position])
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

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