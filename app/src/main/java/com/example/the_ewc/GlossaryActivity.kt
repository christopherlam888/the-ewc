package com.example.the_ewc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

val glossary = arrayListOf<Term>()
var glossarySelected = arrayListOf<Term>()

class GlossaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glossary)

        if (glossary.isEmpty()) {
            glossaryReadJson()
        }

        glossarySelected = glossary

        val glossaryHeaderTextView = findViewById<TextView>(R.id.glossary_header)
        glossaryHeaderTextView.text = "Glossary"

        val recyclerView = findViewById<RecyclerView>(R.id.glossary_recycler_view)
        val adapter = GlossaryAdapter(this, glossarySelected)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        val buttonAll = findViewById<ImageButton>(R.id.buttonAll)
        val buttonBrands = findViewById<ImageButton>(R.id.buttonBrands)
        val buttonGeneral = findViewById<ImageButton>(R.id.buttonGeneral)
        buttonAll.setOnClickListener{
            var selected = arrayListOf<Term>()
            selected = glossary
            glossarySelected = glossary
            adapter.updateData(selected)
            adapter.notifyDataSetChanged()
        }
        buttonBrands.setOnClickListener{
            var selected = arrayListOf<Term>()
            for (term in glossary) {
                if (term.category == "brand") {
                    selected.add(term)
                }
            }
            glossarySelected = selected
            adapter.updateData(selected)
            adapter.notifyDataSetChanged()
        }
        buttonGeneral.setOnClickListener{
            var selected = arrayListOf<Term>()
            for (term in glossary) {
                if (term.category == "general") {
                    selected.add(term)
                }
            }
            glossarySelected = selected
            adapter.updateData(selected)
            adapter.notifyDataSetChanged()
        }

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