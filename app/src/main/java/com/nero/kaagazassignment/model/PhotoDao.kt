package com.nero.kaagazassignment.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PhotoDao {

    @Insert
    fun insertPhoto(photoList: PhotoEntity)

    @Query("SELECT * FROM photoTable")
    fun getPhotoList(): LiveData<List<PhotoEntity>>

}
