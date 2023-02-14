package com.example.the_ewc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

var glossary = arrayListOf<Term>()
var glossarySelected = arrayListOf<Term>()

class GlossaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glossary)

        glossarySelected = glossary

        val recyclerView = findViewById<RecyclerView>(R.id.glossary_recycler_view)
        val adapter = GlossaryAdapter(this, glossarySelected)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        val buttonAll = findViewById<ImageButton>(R.id.buttonAll)
        val buttonBrands = findViewById<ImageButton>(R.id.buttonBrands)
        val buttonGeneral = findViewById<ImageButton>(R.id.buttonGeneral)
        buttonAll.setOnClickListener {
            val selected = glossary
            glossarySelected = glossary
            adapter.updateData(selected)
            adapter.notifyDataSetChanged()
        }
        buttonBrands.setOnClickListener {
            val selected = glossary.filter { it.category == "brand" } as ArrayList<Term>
            glossarySelected = selected
            adapter.updateData(selected)
            adapter.notifyDataSetChanged()
        }
        buttonGeneral.setOnClickListener {
            val selected = glossary.filter { it.category == "general" } as ArrayList<Term>
            glossarySelected = selected
            adapter.updateData(selected)
            adapter.notifyDataSetChanged()
        }

    }
}