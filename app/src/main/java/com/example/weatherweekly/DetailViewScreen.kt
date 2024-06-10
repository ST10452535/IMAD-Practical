package com.example.weatherweekly

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.text.StringBuilder

class DetailViewScreen : AppCompatActivity() {

    // Background from: https://www.canva.com/design/DAGHti5LPMs/upIcscUTzizL8TUDbIDS8w/edit?utm_content=DAGHti5LPMs&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton
    // Retrieved on 10 June 2024

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_view_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val weekdayArray = intent.getStringArrayExtra("weekdayArray")
        val mintempArray = intent.getIntArrayExtra("mintempArray")
        val maxtempArray = intent.getIntArrayExtra("maxtempArray")
        val weathercArray = intent.getStringArrayExtra("weathercArray")

        val txtDetails = findViewById<TextView>(R.id.txtDetails)
        val btnMain = findViewById<Button>(R.id.btnMain)
        val stringBuilder = StringBuilder( )    //Gotten code from my Parallel Arrays Code

        if (weekdayArray!= null)
        {
            for (index in weekdayArray.indices) //(Code Eas, 2024)
            {
                stringBuilder.append("Day: ${weekdayArray[index]}\n")

                if(mintempArray != null && index < mintempArray.size)   //(Kotlin Android, 2024)
                {
                    stringBuilder.append("Min: ${mintempArray[index]}\n")
                }
                if (maxtempArray != null && index < maxtempArray.size)
                {
                    stringBuilder.append("Max: ${maxtempArray[index]}\n")
                }
                if (weathercArray != null && index < weathercArray.size)
                {
                    stringBuilder.append("Weather Condition: ${weathercArray[index]}\n")
                }
            }
        }

        txtDetails.text = stringBuilder.toString()

        btnMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java) //(IIE, 2024)

            startActivity(intent)
        }
    }
}
//Reference List:
//Kotlin Android. 2024.Kotlin Array.idices. Avaiable at: https://kotlinandroid.org/kotlin/array/kotlin-array-indices/ [Accessed 10 June 2024].
//Code Ease. 2024. kotlin size of array, 13 May 2024 [Online]. Avaiable at: https://www.codeease.net/programming/kotlin/kotlin-size-of-array [Accessed 10 June 2024].\
////The IIE. 2024. Introduction to Mobile Application Development [IMAD5112 Module Manual]. The Independent Institute of Education: Unpublished.