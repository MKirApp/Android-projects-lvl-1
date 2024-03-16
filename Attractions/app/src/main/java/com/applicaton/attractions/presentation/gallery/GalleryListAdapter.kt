package com.applicaton.attractions.presentation.gallery

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.applicaton.attractions.databinding.PhotoItemBinding
import com.applicaton.attractions.presentation.gallery.models.PhotoGallery
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class GalleryListAdapter(
    private val onClick: (PhotoGallery) -> Unit,
    private val onDelete: (PhotoGallery) -> Unit
) : ListAdapter<PhotoGallery, PhotoViewHolder>(DiffUtilCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            PhotoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            item?.let {
                Glide
                    .with(photo.context)
                    .load(Uri.parse(item.uri))
                    .into(photo)
            }
            textData.text = Uri.parse(item.uri).extractDateTime()
        }

        holder.binding.root.setOnClickListener {
            item?.let {
                onClick(item)
            }
        }

        holder.binding.buttonDelete.setOnClickListener {
            Log.d("PHOTO", "${item}")
            item?.let {
                onDelete(item)
                Log.d("PHOTO", "${item.isSelected}")
                Log.d("PHOTO", "${item.id} и $position")
            }
        }



    }

}

class PhotoViewHolder(val binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root)

class DiffUtilCallback : DiffUtil.ItemCallback<PhotoGallery>() {
    override fun areItemsTheSame(oldItem: PhotoGallery, newItem: PhotoGallery): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PhotoGallery, newItem: PhotoGallery): Boolean =
        oldItem == newItem

}

fun Uri.extractDateTime(): String? {
    val regex = Regex("""(\d{4})-(\d{2})-(\d{2})-(\d{2})-(\d{2})-(\d{2})""")
    val matchResult = regex.find(this.toString())
    if (matchResult != null) {
        val (year, month, day, hour, minute, second) = matchResult.destructured
        val calendar = Calendar.getInstance()
        calendar.set(
            year.toInt(),
            month.toInt() - 1,
            day.toInt(),
            hour.toInt(),
            minute.toInt(),
            second.toInt()
        )
        val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:SS", Locale.getDefault())
        return formatter.format(calendar.time)
    }
    return "Неверный формат URI"
}