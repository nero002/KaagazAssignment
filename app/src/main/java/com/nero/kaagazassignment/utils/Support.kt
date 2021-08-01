package com.nero.kaagazassignment.utils

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import java.io.File


fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun fileExistOrNot(photoFile: File) {
    if (!photoFile.exists()) {
        Log.d(ContentValues.TAG, "Folder doesn't exist, creating it...")
        val rv = photoFile.mkdir()
        Log.d(ContentValues.TAG, "Folder creation " + if (rv) "success" else "failed")
    } else {
        Log.d(ContentValues.TAG, "Folder already exists.")
    }
}