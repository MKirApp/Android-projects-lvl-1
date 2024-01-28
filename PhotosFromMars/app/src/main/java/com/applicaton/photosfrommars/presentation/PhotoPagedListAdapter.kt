package com.applicaton.photosfrommars.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.applicaton.photosfrommars.databinding.PhotoItemBinding
import com.applicaton.photosfrommars.presentation.models.PhotoModel
import com.bumptech.glide.Glide

class PhotoPagedListAdapter(
    private val onClick: (PhotoModel) -> Unit
) : PagingDataAdapter<PhotoModel, PhotoViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(PhotoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            rover.text = StringBuilder().apply { append("Rover: ",item?.rover?.name)}.toString()
            camera.text = StringBuilder().apply { append("Camera: ",item?.camera?.name)}.toString()
            sol.text = StringBuilder().apply { append("Sol: ",item?.sol)}.toString()
            date.text = StringBuilder().apply { append("Date: ",item?.earth_date)}.toString()
            item?.let {
                Glide
                    .with(imageView.context)
                    .load(item.img_src)
                    .into(imageView)
            }
        }
        holder.binding.cardView.setOnClickListener {
            item?.let {
                onClick(item)
            }
        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<PhotoModel>() {
    override fun areItemsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean = oldItem == newItem

}

class PhotoViewHolder(val binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root)