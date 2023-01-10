package com.example.the_ewc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

val videos = arrayListOf<Video>()

class VideosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videos)

        if (videos.isEmpty()) {
            videosReadJson()
        }

        val videosHeaderTextView = findViewById<TextView>(R.id.videos_header)
        videosHeaderTextView.text = "Videos"

        val recyclerView = findViewById<RecyclerView>(R.id.videos_recycler_view)
        recyclerView.adapter = VideosAdapter(this, videos)
        recyclerView.setHasFixedSize(true)

    }
    private fun videosReadJson() {
        var json : String? = null
        try {
            val inputStream: InputStream = assets.open("videos.json")
            json = inputStream.bufferedReader().use{it.readText()}
            val jsonArray = JSONArray(json)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                videos.add(Video(jsonObject.getString("title"), jsonObject.getString("thumbnail"), jsonObject.getString("url")))
            }
        }
        catch (_: IOException)
        {
        }
    }
}