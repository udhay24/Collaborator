package com.collaborator.android

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.io.File

class ImageAdapter(private val list: List<Uri>): RecyclerView.Adapter<ImageAdapter.ImageViewholder>() {

    val imageList = list.toMutableList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewholder {
     return ImageViewholder(ImageView(parent.context))
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageViewholder, position: Int) {
        holder.bindToViewHolder(imageList[position])
    }

    fun addNewItem(file: Uri) {
        imageList.add(file)
        notifyItemInserted(imageList.size - 1)
    }

    inner class ImageViewholder(val view: ImageView): RecyclerView.ViewHolder(view) {
        fun bindToViewHolder(file: Uri) {
            Picasso.get()
                .load(file)
                .resize(200, 200)
                .into(view)
        }
    }
}