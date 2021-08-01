package com.nero.kaagazassignment.model

import android.widget.ImageView

interface OnClickPhoto {

    fun onClick(photoEntity: PhotoEntity, mIvImage: ImageView)
}