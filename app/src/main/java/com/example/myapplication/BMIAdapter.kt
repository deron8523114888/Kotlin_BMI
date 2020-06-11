package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhy.adapter.recyclerview.CommonAdapter


class TestAdaper(val arrayList: ArrayList<BMIBean>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bmi_show, null, false)
        val ViewHolder = ViewHolder(view)
        return ViewHolder
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val num = position + 1
        val BMI: TextView = holder.itemView.findViewById(R.id.tv_bmi)

        BMI.setText("第" + num + "筆：" + "身高：" + arrayList[position].Height
                + ",體重：" + arrayList[position].Weight
                + ",BMI：" + arrayList[position].BMI)
    }
}


class CommonAdaper(context: Context,
                   val arrayList: ArrayList<BMIBean>) : CommonAdapter<BMIBean>(context, R.layout.item_bmi_show, arrayList) {

    override fun convert(holder: com.zhy.adapter.recyclerview.base.ViewHolder?, t: BMIBean?, position: Int) {
        holder?.setText(R.id.tv_bmi,
                "BMI:" + arrayList[position].BMI
                        + "   |   身高:" + arrayList[position].Height
                        + "   |   體重:" + arrayList[position].Weight)
    }
}


class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)