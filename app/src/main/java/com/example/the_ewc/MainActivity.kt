package com.example.the_ewc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val glossaryButton = findViewById<ImageButton>(R.id.glossary_button)
        val videosButton = findViewById<ImageButton>(R.id.videos_button)
        glossaryButton.setOnClickListener{
            val intent = Intent(this, GlossaryActivity::class.java)
            startActivity(intent)
        }
        videosButton.setOnClickListener{
            val intent = Intent(this, VideosActivity::class.java)
            startActivity(intent)
        }
    }
}