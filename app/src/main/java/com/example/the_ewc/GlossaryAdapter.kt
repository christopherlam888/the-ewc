package com.example.the_ewc

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class GlossaryAdapter(
    private val context: Context,
    private var dataset: List<Term>
) : RecyclerView.Adapter<GlossaryAdapter.GlossaryViewHolder>() {

    class GlossaryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val button: Button = view.findViewById(R.id.term_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlossaryViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.term, parent, false)

        return GlossaryViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: GlossaryViewHolder, position: Int) {
        val item = dataset[position]
        holder.button.text = item.term
        holder.button.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, TermDetailsActivity::class.java)
            intent.putExtra("position", position.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataset.size

    fun updateData(newDataset: List<Term>) {
        dataset = newDataset
    }

}