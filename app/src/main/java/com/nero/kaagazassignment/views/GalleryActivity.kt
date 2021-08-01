package com.nero.kaagazassignment.views

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.nero.kaagazassignment.databinding.ActivityGalleryBinding
import com.nero.kaagazassignment.model.OnClickPhoto
import com.nero.kaagazassignment.model.PhotoEntity
import com.nero.kaagazassignment.viewmodel.CameraViewModel
import com.nero.kaagazassignment.views.rv.PhotoAdapter
import com.nero.kaagazassignment.views.rv.PreviewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryActivity : AppCompatActivity(), OnClickPhoto {

    private val viewModel by viewModels<CameraViewModel>()

    private lateinit var binding: ActivityGalleryBinding

    private var photoList = arrayListOf<PhotoEntity>()

    lateinit var cameraAdapter: PhotoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)

        setContentView(binding.root)


        cameraAdapter = PhotoAdapter(photoList, this)

        binding.rvPhoto.apply {
            adapter = cameraAdapter
            layoutManager = GridLayoutManager(this@GalleryActivity, 4)
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

    override fun onClick(photoEntity: PhotoEntity, mIvImage: ImageView) {
        val intent = Intent(this, PreviewActivity::class.java)
        intent.putExtra("photo", photoEntity)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            mIvImage,
            ViewCompat.getTransitionName(mIvImage)!!
        )
        startActivity(intent, options.toBundle())
    }


}
