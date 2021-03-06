package com.nero.kaagazassignment.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "photoTable")
data class PhotoEntity(

    @ColumnInfo(name = "photoName")
    var photoName: String,
    @ColumnInfo(name = "photoLocation")
    var photoLocation: String,
    @ColumnInfo(name = "timeStamp")
    var timeStamp: Long

): Parcelable  {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}