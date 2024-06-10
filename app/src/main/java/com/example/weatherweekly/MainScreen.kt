package com.example.weatherweekly

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainScreen : AppCompatActivity() {

    // Background from: https://www.canva.com/design/DAGHti5LPMs/upIcscUTzizL8TUDbIDS8w/edit?utm_content=DAGHti5LPMs&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton
    // Retrieved on 10 June 2024

    private lateinit var edtDay: EditText
    private lateinit var edtMin: EditText
    private lateinit var edtMax: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnAverage: Button
    private lateinit var btnClear: Button
    private lateinit var txtAverage: TextView
    private lateinit var btnExit2: Button
    private lateinit var btnNext2: Button

    private val arraySize = 7

    private var weekdayArray = Array(arraySize){""}
    private var mintempArray = IntArray(arraySize)
    private var maxtempArray = IntArray(arraySize)
    private var weathercArray = Array(arraySize){""}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtDay = findViewById<EditText>(R.id.edtDay)
        edtMin = findViewById<EditText>(R.id.edtMin)
        edtMax =  findViewById<EditText>(R.id.edtMax)
        btnAdd = findViewById<Button>(R.id.btnAdd)
        btnAverage = findViewById<Button>(R.id.btnAverage)
        btnClear = findViewById<Button>(R.id.btnClear)
        txtAverage = findViewById<TextView>(R.id.txtAverage)
        btnExit2 = findViewById<Button>(R.id.btnExit2)
        btnNext2 = findViewById<Button>(R.id.btnNext2)

        btnAdd.setOnClickListener { weatherAdd() }
        btnAverage.setOnClickListener { averageCalc() }
        btnClear.setOnClickListener { clearInfo() }
        btnExit2.setOnClickListener { exitApp() }
        btnNext2.setOnClickListener { viewDetails() }
    }
    private fun weatherAdd(){
        val weekDay = edtDay.text.toString()
        val minTemp = edtMin.text.toString().toIntOrNull()
        val maxTemp = edtMax.text.toString().toIntOrNull()
        var index = 0

        if (weekDay.isEmpty())
        {
            Toast.makeText(this, "Please enter a day", Toast.LENGTH_SHORT).show()
        }

        if (minTemp == null)
        {
            Toast.makeText(this, "Please enter a valid Minimum Temperature", Toast.LENGTH_SHORT).show()
        }

        if (maxTemp == null)
        {
            Toast.makeText(this, "Please enter a valid Maximum Temperature", Toast.LENGTH_SHORT).show()
        }

        if (weekDay.isNotEmpty() && minTemp != null && maxTemp != null)
        {
            while (index < arraySize && weekdayArray[index].isNotEmpty())
            {
                index += 1
            }

            if (index < arraySize)
            {
                weekdayArray[index] = weekDay
                mintempArray[index] = minTemp
                maxtempArray[index] = maxTemp

                weathercArray[index] =
                    if (maxTemp < 10)
                    {
                        "Cold"
                    }
                    else if(maxTemp < 20)
                    {
                        "Slight Breeze, could rain"
                    }
                    else if (maxTemp < 30)
                    {
                        "Sunny"
                    } else
                    {
                        "Extremely Hot"
                    }

            edtDay.text.clear()
            edtMin.text.clear()
            edtMax.text.clear()
            }
        }
    }

    private fun averageCalc(){
        var calcTotalmin = 0
        var calcTotalmax = 0
        var index = 0

        while (index < arraySize)
        {
            calcTotalmin += mintempArray[index]
            calcTotalmax += maxtempArray[index]
            index++
        }

        val temperatureSum = calcTotalmin + calcTotalmax
        val weeklyAverage = temperatureSum / arraySize

        txtAverage.text = "The weekly average is $weeklyAverage degrees"
    }

    private fun clearInfo(){
        weekdayArray = Array(arraySize){""}
        mintempArray = IntArray(arraySize)
        maxtempArray = IntArray(arraySize)
        weathercArray = Array(arraySize){""}
        txtAverage.text = ""
    }

    private fun exitApp(){
        finish()    //(Code Ease, 2023)
    }

    private fun viewDetails(){
        val intent = Intent(this, DetailViewScreen::class.java)
        intent.putExtra("weekdayArray", weekdayArray)   //(Allan, 2010)
        intent.putExtra("mintempArray", mintempArray)   //(Allan, 2010)
        intent.putExtra("maxtempArray", maxtempArray)   //(Allan, 2010)
        intent.putExtra("weathercArray", weathercArray) //(Allan, 2010)

        startActivity(intent)
    }
}
//Reference List:
//Allan.2010.In Android: How do I get variables/ data from one screen to another [Source code]. https://stackoverflow.com/questions/2347152/in-android-how-do-i-get-variables-data-from-one-screen-to-another (Accessed 10 June 2024)
////Code Ease. 2023. how to close android app programmatically, 28 December 2023. [Online]. Available at: https://www.codeease.net/programming/java/how-to-close-android-app-programmatically [Accessed 10 June 2024].