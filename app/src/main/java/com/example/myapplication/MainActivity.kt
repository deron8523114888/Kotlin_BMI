package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.log

class MainActivity : AppCompatActivity() {


    val array = IntArray(5);
    val array_= intArrayOf(123,44);
    val array_name = arrayOfNulls<String>(5);
    val array_name_ = arrayOf(true,false,true,0.8,5,"wqjo");


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var num = 1 ;

        for ( i in 0 until 5){
            array[i] = num
            num++;
            Log.v("test_",array[i].toString());
        }


    }

    private fun count (x : Int , y : String) {


    }
    
    

}