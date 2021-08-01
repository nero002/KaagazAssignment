package com.nero.kaagazassignment.repository

import androidx.lifecycle.LiveData
import com.nero.kaagazassignment.model.PhotoDatabase
import com.nero.kaagazassignment.model.PhotoEntity
import javax.inject.Inject

class PhotoRepository @Inject constructor(database: PhotoDatabase) {

    private val dao = database.getCameraDao()

    fun addPhoto(photoEntity: PhotoEntity) {
        dao.insertPhoto(photoEntity)
    }

    fun getPhotoList(): LiveData<List<PhotoEntity>> {
        return dao.getPhotoList()
    }


}