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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val BMI_List = ArrayList<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Init()
        Click()

    }


    private fun Init(){


        var linearLayoutManager  =  LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        BMI_RecyclerView.layoutManager = linearLayoutManager

    }

    private fun Click(){


        Calculate.setOnClickListener(View.OnClickListener {
            try {
                val BMI = Edit_Weight.text.toString().toDouble()/Math.pow(Edit_Height.text.toString().toDouble(),2.0)
                BMI_List.add(BMI)
                Dialog(true)
                BMI_RecyclerView.adapter = TestAdaper(BMI_List)
                BMI_RecyclerView.scrollToPosition(BMI_List.size-1)

            }catch (E : NumberFormatException){
                Dialog(false)
            }
        })


        Reset.setOnClickListener(View.OnClickListener {
            Edit_Height.text.clear()
            Edit_Weight.text.clear()

        })

    }
    

    private fun Dialog(boolean: Boolean){

        val dialog = AlertDialog.Builder(this)
        if(boolean){
            dialog.setTitle("成功")
            dialog.setMessage("請於下方查看BMI")
            dialog.setCancelable(false)
            dialog.setNegativeButton("了解", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
        }else{
            dialog.setTitle("失敗")
            dialog.setMessage("請輸入正確的身高、體重")
            dialog.setCancelable(false)
            dialog.setNegativeButton("了解", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()

            })
        }
        dialog.show()

    }

    class TestAdaper(var arrayList: ArrayList<Double>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            Log.v("test_", "onCreateViewHolder")
            val view = LayoutInflater.from(parent.context).inflate(R.layout.layout, null, false)
            val ViewHolder = ViewHolder(view)
            return  ViewHolder
        }

        override fun getItemCount(): Int {
            Log.v("test_", "getItemCount")
            return arrayList.size

        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            Log.v("test_", "onBindViewHolder")
            val num = position+1
            val BMI : TextView = holder.itemView.findViewById(R.id.BMI_)
            BMI.setText("第"+num+"次測試："+arrayList[position])
        }
    }

    class ViewHolder( itemView: View ) : RecyclerView.ViewHolder(itemView)

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