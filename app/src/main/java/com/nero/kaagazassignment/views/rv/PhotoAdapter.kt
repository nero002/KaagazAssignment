package com.nero.kaagazassignment.views.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nero.kaagazassignment.databinding.GalleryItemLayouBinding
import com.nero.kaagazassignment.model.PhotoEntity

class PhotoAdapter(private var photoList: List<PhotoEntity>) :
    RecyclerView.Adapter<CameraViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraViewHolder {
        val view =
            GalleryItemLayouBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CameraViewHolder(view)
    }

    override fun onBindViewHolder(holder: CameraViewHolder, position: Int) {
        val albumEntity = photoList[position]
        holder.setData(albumEntity)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }
}