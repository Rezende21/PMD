package com.example.pmd

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import android.util.Log.e as e

class MainActivity : AppCompatActivity() {

    private lateinit var txt_result: TextView
    private lateinit var edit_meta: EditText
    private lateinit var edit_venda: EditText
    private lateinit var bt_calcular: Button
    private lateinit var AdView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializationViews()

        MobileAds.initialize(this) {}

        val adRequest = AdRequest.Builder().build()
        AdView.loadAd(adRequest)

        bt_calcular.setOnClickListener {
            try {
                setResult()
            }catch (e: Exception) {
                Toast.makeText(this,"Precisa fornecer a meta e a venda para fazer o calculo", Toast.LENGTH_LONG).show()
                Log.e("PWD", "catch by trying to make the function setResult()")
            }
        }
    }

    private fun initializationViews() {
        txt_result = findViewById(R.id.txt_resul)
        edit_meta = findViewById(R.id.edit_meta)
        edit_venda = findViewById(R.id.edit_venda)
        bt_calcular = findViewById(R.id.bt_calcular)
        AdView = findViewById(R.id.google_ads)
    }

    @SuppressLint("SetTextI18n")
    private fun setResult() {
        var result: Double
        val day_goal: Double
        val day_sel: Double
        day_goal = edit_meta.text.toString().toDouble()
        day_sel = edit_venda.text.toString().toDouble()
        result = day_sel / day_goal
        result -= 1
        result *= 100

        if (result < 0) {
            txt_result.setText("$result%")
            txt_result.setTextColor(getColor(R.color.conta_menor_0))
        } else {
            txt_result.setText("$result%")
            txt_result.setTextColor(getColor(R.color.black))
        }
    }

}