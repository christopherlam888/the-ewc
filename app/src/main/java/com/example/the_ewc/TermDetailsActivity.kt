package com.example.the_ewc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TermDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_term_details)

        val position = intent?.extras?.getString("position")?.toInt()

        val termTextView = findViewById<TextView>(R.id.term_details_term)
        val definitionTextView = findViewById<TextView>(R.id.term_details_definition)
        val categoryTextView = findViewById<TextView>(R.id.term_details_category)

        termTextView.text = glossary[position!!].term
        definitionTextView.text = glossary[position].definition
        categoryTextView.text = glossary[position].category

    }
}