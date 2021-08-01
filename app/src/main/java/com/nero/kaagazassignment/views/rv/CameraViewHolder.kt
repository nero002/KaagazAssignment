package com.nero.kaagazassignment.views.rv

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.nero.kaagazassignment.databinding.GalleryItemLayouBinding
import com.nero.kaagazassignment.model.OnClickPhoto
import com.nero.kaagazassignment.model.PhotoEntity

class CameraViewHolder(
    private val binding: GalleryItemLayouBinding,
    private val onItemClickListener: OnClickPhoto
) :

    RecyclerView.ViewHolder(binding.root) {

    fun setData(photoEntity: PhotoEntity) {
        binding.apply {

            Glide.with(ivCoverImage).load(photoEntity.photoLocation).into(ivCoverImage)

            ivCoverImage.setOnClickListener {
                onItemClickListener.onClick(photoEntity, ivCoverImage)
            }
        }
    }
}