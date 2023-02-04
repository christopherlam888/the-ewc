package com.example.the_ewc

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VideosAdapter(
    private val context: Context,
    private var dataset: List<Video>
) : RecyclerView.Adapter<VideosAdapter.VideoViewHolder>() {

    class VideoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.video_title)
        val imageButton: ImageButton = view.findViewById(R.id.video_image_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.video, parent, false)

        return VideoViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = item.title
        var thumbnail = ""
        if (item.thumbnail.isEmpty()) {
            thumbnail = "thumbnail"
        }
        else {
            thumbnail = item.thumbnail
        }
        val thumbnailId = context.resources.getIdentifier(thumbnail, "drawable", context.packageName)
        holder.imageButton.setImageResource(thumbnailId)
        holder.imageButton.setOnClickListener{
            val queryUrl: Uri = Uri.parse(item.url)
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataset.size

    fun updateData(newDataset: List<Video>) {
        dataset = newDataset
    }
}