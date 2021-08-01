package com.nero.kaagazassignment.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.core.view.ViewCompat
import com.google.common.util.concurrent.ListenableFuture
import com.nero.kaagazassignment.databinding.ActivityMainBinding
import com.nero.kaagazassignment.model.PhotoEntity
import com.nero.kaagazassignment.utils.Constants.CAMERA_REQUEST_CODE
import com.nero.kaagazassignment.utils.fileExistOrNot
import com.nero.kaagazassignment.utils.toast
import com.nero.kaagazassignment.viewmodel.CameraViewModel
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.EasyPermissions
import java.io.File
import java.util.concurrent.ExecutorService

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    lateinit var binding: ActivityMainBinding
    var camera: Camera? = null
    var preview: Preview? = null
    var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService

    private val viewModel by viewModels<CameraViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA
            ) == PERMISSION_GRANTED
        ) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE
            )
        }
        binding.ibCapture.setOnClickListener {
            ClickPhoto()
        }


        binding.ibPreview.setOnClickListener {
            val intent = Intent(this, GalleryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun ClickPhoto() {

        val photoFile = File(
            getExternalFilesDir(Environment.DIRECTORY_DCIM),
            "IMG - ${System.currentTimeMillis()}.jpg"
        )

        fileExistOrNot(photoFile)

        val output = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture?.takePicture(
            output, ContextCompat.getMainExecutor(this),

            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    toast("Image saved")

                    val path = Uri.fromFile(photoFile)
                    binding.ibPreview.setImageURI(path)

                    val photoLocation = photoFile.absolutePath.toString()
                    val photoName = photoFile.name.toString()
                    val timeStamp = System.currentTimeMillis()
                    viewModel.addPhoto(PhotoEntity( photoName,photoLocation, timeStamp))

                }

                override fun onError(exception: ImageCaptureException) {
                    toast("Something went wrong")
                }
            })
    }


    private fun startCamera() {
        val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> =
            ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            preview = Preview.Builder().build()

            preview?.setSurfaceProvider(binding.cPreview.surfaceProvider)
            imageCapture = ImageCapture.Builder().build()


            val cameraSelector =
                CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
            cameraProvider.unbindAll()
            camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
        }, ContextCompat.getMainExecutor(this))
    }


    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        startCamera()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        toast("Camera permission Required")
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }


}

