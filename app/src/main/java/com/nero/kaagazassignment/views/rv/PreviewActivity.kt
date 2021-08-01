package com.nero.kaagazassignment.views.rv

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.nero.kaagazassignment.databinding.ActivityPreviewBinding
import com.nero.kaagazassignment.model.PhotoEntity

class PreviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreviewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val photo = intent.getParcelableExtra<PhotoEntity>("photo")
        val image = photo?.photoLocation
        val date = photo?.timeStamp

        Glide.with(this).load(image).into(binding.ivImage)
        binding.tvName.text = "IMG- ${date}.jpg"

        binding.ibshare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
            intent.putExtra(Intent.EXTRA_TEXT, image)
            startActivity(
                Intent.createChooser(intent, "Share Via")
            )
        }

        binding.deleteButton.setOnClickListener {
            showDeleteDialog()
        }

    }


    /**
     * Alert dialog for delete the image.
     */
    private fun showDeleteDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Kaagaz Scanner")
            .setMessage("Are you sure to Delete this photo")
            .setPositiveButton("Delete") { dialog, _ ->
                dialog.dismiss()
                setResult(Activity.RESULT_OK, intent)
                onBackPressed()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
        builder.create()
        builder.show()
    }
}
