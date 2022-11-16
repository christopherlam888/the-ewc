package com.example.the_ewc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

val glossary = arrayListOf<Term>()

class GlossaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glossary)

        if (glossary.isEmpty()) {
            glossaryReadJson()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.glossary_recycler_view)
        recyclerView.adapter = GlossaryAdapter(this, glossary)
        recyclerView.setHasFixedSize(true)

    }
    private fun glossaryReadJson() {
        var json : String? = null
        try {
            val inputStream: InputStream = assets.open("glossary.json")
            json = inputStream.bufferedReader().use{it.readText()}
            val jsonArray = JSONArray(json)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                glossary.add(Term(jsonObject.getString("term"), jsonObject.getString("definition"), jsonObject.getString("category")))
            }
        }
        catch (_: IOException)
        {
        }
    }
}