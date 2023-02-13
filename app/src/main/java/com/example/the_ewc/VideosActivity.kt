package com.example.the_ewc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

var videos = arrayListOf<Video>()
var reversed = false

class VideosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videos)

        if (videos.isEmpty()) {
            videosReadJson()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.videos_recycler_view)
        val adapter = VideosAdapter(this, videos)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        val buttonForward = findViewById<ImageButton>(R.id.buttonForward)
        val buttonReverse = findViewById<ImageButton>(R.id.buttonReverse)
        buttonForward.setOnClickListener {
            if (reversed) {
                reversed = false
                videos.reverse()
                adapter.updateData(videos)
                adapter.notifyDataSetChanged()
            }
        }
        buttonReverse.setOnClickListener {
            if (!reversed) {
                reversed = true
                videos.reverse()
                adapter.updateData(videos)
                adapter.notifyDataSetChanged()
            }
        }

    }

    private fun videosReadJson() {
        var json: String? = null
        try {
            val inputStream: InputStream = assets.open("videos.json")
            json = inputStream.bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(json)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                videos.add(
                    Video(
                        jsonObject.getString("title"),
                        jsonObject.getString("thumbnail"),
                        jsonObject.getString("url")
                    )
                )
            }
        } catch (_: IOException) {
        }
    }
}