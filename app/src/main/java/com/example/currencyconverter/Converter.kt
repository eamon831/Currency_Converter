package com.example.currencyconverter

import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt


class Converter : AppCompatActivity() {
    lateinit var input:EditText
    lateinit var convert:Button
    lateinit var usdd:soup.neumorphism.NeumorphCardView
    lateinit var eurd:soup.neumorphism.NeumorphCardView
    lateinit var cadd:soup.neumorphism.NeumorphCardView
    lateinit var nzdd:soup.neumorphism.NeumorphCardView
    lateinit var bdtd:soup.neumorphism.NeumorphCardView
    lateinit var vusd:TextView
    lateinit var veur:TextView
    lateinit var vnzd:TextView
    lateinit var vbdt:TextView
    lateinit var vcad:TextView
    private val client = OkHttpClient()
    lateinit var code:String
    var link="https://v6.exchangerate-api.com/v6/16e8c1039211d94d44a26d76/latest/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)
        input=findViewById<EditText>(R.id.amount)
        convert=findViewById<Button>(R.id.convert)
        link+=intent.getStringExtra("name")
        code= intent.getStringExtra("name").toString()

        usdd=findViewById(R.id.cardusd)
        eurd=findViewById(R.id.cardeur)
        cadd=findViewById(R.id.cardcad)
        nzdd=findViewById(R.id.cardnzd)
        bdtd=findViewById(R.id.cardbdt)

        vusd=findViewById(R.id.tvusd)
        vcad=findViewById(R.id.tvcad)
        vbdt=findViewById(R.id.tvbdt)
        vnzd=findViewById(R.id.tvnzd)
        veur=findViewById(R.id.tveur)


        if (Build.VERSION.SDK_INT > 9) {
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        convert.setOnClickListener(){
           // Toast.makeText(this,link,Toast.LENGTH_LONG).show()

            check();

        }
    }

    private fun check() {
        val s=input.text.toString()
        if(s.isEmpty()){
            input.setError("Please Input Amount")
            input.requestFocus()
            return

        }
        run();

    }
    fun run() {
        val request = Request.Builder()
            .url(link)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            else{
              // Toast.makeText(this,response.toString(),Toast.LENGTH_LONG).show()
                var str_response = response.body!!.string()
                val json_contact:JSONObject = JSONObject(str_response)
                var jsonarray_info: JSONObject = json_contact.getJSONObject("conversion_rates")
               // Toast.makeText(this,jsonarray_info.getString("USD"),Toast.LENGTH_LONG).show()
                val usd=jsonarray_info.getString("USD")
                val eur=jsonarray_info.getString("EUR")
                val cad=jsonarray_info.getString("CAD")
                val nzd=jsonarray_info.getString("NZD")
                val bdt=jsonarray_info.getString("BDT")
                usdd.isVisible=true
                cadd.isVisible=true
                bdtd.isVisible=true
                nzdd.isVisible=true
                eurd.isVisible=true
                val temp=input.text.toString()
                val df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.DOWN
                vusd.setText(temp+" "+code+" = "+df.format(usd.toDouble()*temp.toDouble()).toString()+" USD")
                veur.setText(temp+" "+code+" = "+df.format(eur.toDouble()*temp.toDouble()).toString()+" EUR")
                vcad.setText(temp+" "+code+" = "+df.format(cad.toDouble()*temp.toDouble()).toString()+" CAD")
                vnzd.setText(temp+" "+code+" = "+df.format(nzd.toDouble()*temp.toDouble()).toString()+" NZD")
                vbdt.setText(temp+" "+code+" = "+df.format(bdt.toDouble()*temp.toDouble()).toString()+" BDT")
            }


        }
    }
}