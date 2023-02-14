package com.example.the_ewc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (glossary.isEmpty()) {
            val networkRequest = NetworkRequest(this)
            networkRequest.getData {}
        }

        if (glossary.isEmpty()) {
            glossaryReadJson()
        }

        val glossaryTextView = findViewById<TextView>(R.id.glossary_title)
        glossaryTextView.text = getString(R.string.glossary)
        val videosTextView = findViewById<TextView>(R.id.videos_title)
        videosTextView.text = getString(R.string.videos)
        val glossaryButton = findViewById<ImageButton>(R.id.glossary_button)
        val videosButton = findViewById<ImageButton>(R.id.videos_button)
        glossaryButton.setOnClickListener {
            val intent = Intent(this, GlossaryActivity::class.java)
            startActivity(intent)
        }
        videosButton.setOnClickListener {
            val intent = Intent(this, VideosActivity::class.java)
            startActivity(intent)
        }
    }

    private fun glossaryReadJson() {
        var json: String? = null
        try {
            val inputStream: InputStream = assets.open("glossary.json")
            json = inputStream.bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(json)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                glossary.add(
                    Term(
                        jsonObject.getString("term"),
                        jsonObject.getString("definition"),
                        jsonObject.getString("category")
                    )
                )
            }
        } catch (_: IOException) {
        }
    }
}