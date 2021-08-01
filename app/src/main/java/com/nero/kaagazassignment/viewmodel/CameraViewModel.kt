package com.nero.kaagazassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nero.kaagazassignment.model.PhotoEntity
import com.nero.kaagazassignment.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(private val photoRepository: PhotoRepository) :
    ViewModel() {


    fun addPhoto(photoEntity: PhotoEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            photoEntity.id
            photoRepository.addPhoto(photoEntity)
        }
    }

    fun getPhotoList(): LiveData<List<PhotoEntity>> {
        return photoRepository.getPhotoList()
    }


}