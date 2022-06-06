package com.example.currencyconverter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val usd=findViewById<Button>(R.id.USD)
        val eur=findViewById<Button>(R.id.EUR)
        val cad=findViewById<Button>(R.id.CAD)
        val nzd=findViewById<Button>(R.id.NZD)
        val bdt=findViewById<Button>(R.id.BDT)
        usd.setOnClickListener(){
           // Toast.makeText(this,"Testing",Toast.LENGTH_LONG).show()
            val intent=Intent(this,Converter::class.java)
            intent.putExtra("name","USD")
            startActivity(intent)
        }
        eur.setOnClickListener(){
            // Toast.makeText(this,"Testing",Toast.LENGTH_LONG).show()
            val intent=Intent(this,Converter::class.java)
            intent.putExtra("name","EUR")
            startActivity(intent)
        }
        cad.setOnClickListener(){
            // Toast.makeText(this,"Testing",Toast.LENGTH_LONG).show()
            val intent=Intent(this,Converter::class.java)
            intent.putExtra("name","CAD")
            startActivity(intent)
        }
        nzd.setOnClickListener(){
            // Toast.makeText(this,"Testing",Toast.LENGTH_LONG).show()
            val intent=Intent(this,Converter::class.java)
            intent.putExtra("name","NZD")
            startActivity(intent)
        }
        bdt.setOnClickListener(){
            // Toast.makeText(this,"Testing",Toast.LENGTH_LONG).show()
            val intent=Intent(this,Converter::class.java)
            intent.putExtra("name","BDT")
            startActivity(intent)
        }
    }
}