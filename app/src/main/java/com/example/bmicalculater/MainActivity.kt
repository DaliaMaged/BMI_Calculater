package com.example.bmicalculater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val heightTxt=findViewById<EditText>(R.id.height_edt)
        val weightTxt=findViewById<EditText>(R.id.weight_edt)
        val calculate=findViewById<Button>(R.id.calculate_btn)
        val cardResult=findViewById<CardView>(R.id.cardResult)

        calculate.setOnClickListener {
            val height=heightTxt.text.toString()
            val weight=weightTxt.text.toString()
            if (validateInput(height,weight)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                //get result with 2 num float 3shan el rakm 3ashry
                val bmi2Digit = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digit)
                cardResult.visibility = View.VISIBLE
            }

        }

    }

    private fun validateInput(height: String?, weight: String?):Boolean {
       return when {
            height.isNullOrEmpty() ->{
                Toast.makeText(this,"Please enter your height",Toast.LENGTH_SHORT).show()
                return false
            }
            weight.isNullOrEmpty() ->{
                Toast.makeText(this,"Please enter your weight",Toast.LENGTH_SHORT).show()
                return false
            }
           else -> {true}
       }


    }

    private fun displayResult(bmi: Float) {
        val resultTxt=findViewById<TextView>(R.id.resut)
        val resultPhrase=findViewById<TextView>(R.id.result_phrase)
        val info=findViewById<TextView>(R.id.info)
        resultTxt.text=bmi.toString()
        info.text="The normal is from 18 to 24"
        var resultPh=""
        var color=0
        when{
            bmi<18.50 ->{
                resultPh="under weight"
                 color=R.color.under_weight
            }
            bmi in 18.50..24.99->{
                resultPh="normal weight"
                color=R.color.normal
            }
            bmi in 25.00..29.99->{
                resultPh="over weight"
           color=R.color.over_weight
            }
            bmi > 29.99 -> {
                resultPh="obese"
                color=R.color.obese
            }
        }

        resultPhrase.setTextColor(ContextCompat.getColor(this,color))
        resultPhrase.text=resultPh
        }
//        when{
//            bmi in 18..24.99.toInt() ->{
//
//            }
//        }
//        if (bmi < 18){
//            resultPh="under weight"
//            color=R.color.under_weight
//        }
//        else if (bmi in 18..24.99.toInt()){
//            resultPh="under weight"
//            color=R.color.under_weight
//
//        }

//        for (bmi in 0..18){
//            resultPh="under weight"
//            color=R.color.under_weight
//        }
//        for (bmi in 18..24){
//            resultPh="normal weight"
//            color=R.color.normal
//        }
//        for (bmi in 25..29.99.toInt()){
//            resultPh="over weight"
//            color=R.color.over_weight
//        }
//        for (bmi in 30..60){
//            resultPh="obese"
//            color=R.color.obese
//        }
}