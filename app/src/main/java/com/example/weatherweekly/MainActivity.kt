package com.example.weatherweekly

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Background from: https://www.canva.com/design/DAGHti5LPMs/upIcscUTzizL8TUDbIDS8w/edit?utm_content=DAGHti5LPMs&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton
    // Retrieved on 10 June 2024

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnNextPage = findViewById<Button>(R.id.btnNext)
        val btnExitApp = findViewById<Button>(R.id.btnExit)

        btnNextPage.setOnClickListener {
            val intent = Intent(this, MainScreen::class.java)    //When clicked it will open the Main Screen Activity Page(IIE, 2024)

            startActivity(intent)
        }

        btnExitApp.setOnClickListener {
            finish()     // Although this code is used in java it will still effectively close the application successfully  (Code Ease, 2023)
        }
    }
}

//Reference List:
//The IIE. 2024. Introduction to Mobile Application Development [IMAD5112 Module Manual]. The Independent Institute of Education: Unpublished.
//Code Ease. 2023. how to close android app programmatically, 28 December 2023. [Online]. Available at: https://www.codeease.net/programming/java/how-to-close-android-app-programmatically [Accessed 10 June 2024].