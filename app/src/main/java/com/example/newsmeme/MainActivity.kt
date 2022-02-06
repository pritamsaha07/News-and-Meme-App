package com.example.newsmeme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun meme(view: android.view.View) {


        val button=findViewById<Button>(R.id.button)
        button.setOnClickListener{
            val intent = Intent(this,meme::class.java)

            startActivity(intent)
        }
    }

    fun news(view: android.view.View) {

        val button=findViewById<Button>(R.id.button2)
        button.setOnClickListener{
            val intent = Intent(this,news::class.java)

            startActivity(intent)
        }
    }
}