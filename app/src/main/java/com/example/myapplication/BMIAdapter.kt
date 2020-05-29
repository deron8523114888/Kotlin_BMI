package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TestAdaper(val arrayList: ArrayList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
        BMI.setText("第" + num + "筆" + arrayList.get(position))
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)