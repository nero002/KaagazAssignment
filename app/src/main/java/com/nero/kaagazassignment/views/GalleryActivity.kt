package com.nero.kaagazassignment.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.nero.kaagazassignment.databinding.ActivityGalleryBinding
import com.nero.kaagazassignment.model.PhotoEntity
import com.nero.kaagazassignment.viewmodel.CameraViewModel
import com.nero.kaagazassignment.views.rv.PhotoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryActivity : AppCompatActivity() {

    private val viewModel by viewModels<CameraViewModel>()

    private lateinit var binding: ActivityGalleryBinding

    private var photoList = arrayListOf<PhotoEntity>()

    lateinit var cameraAdapter: PhotoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)

        setContentView(binding.root)


        cameraAdapter = PhotoAdapter(photoList)

        binding.rvPhoto.apply {
            adapter = cameraAdapter
            layoutManager = GridLayoutManager(this@GalleryActivity, 2)
        }

        viewModel.getPhotoList().observe(this, Observer {
            if (it != null) {
                updateRecyclerView(it)
            }
        })


    }

    private fun updateRecyclerView(list: List<PhotoEntity>) {
        photoList.clear()
        photoList.addAll(list)
        cameraAdapter.notifyDataSetChanged()
    }


}
