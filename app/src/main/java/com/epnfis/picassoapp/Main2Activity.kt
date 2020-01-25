package com.epnfis.picassoapp

import android.Manifest
import android.content.ContentUris
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        if (!hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE))
            checkForPermission()

        var listadoImagenes = getImagesPath()

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE
        )

        val query = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            MediaStore.Images.Media._ID
        )
        query?.use { cursor ->
            // Cache column indices.
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            //val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            //val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
            //if (cursor != null)
            //    cursor.moveToFirst()
            while (cursor.moveToNext()) {
                // Get values of columns for a given video.
                val id = cursor.getLong(idColumn)
                //val name = cursor.getString(nameColumn)
                //val size = cursor.getInt(sizeColumn)
                val contentUri: Uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                Picasso.get().load(contentUri.toString()).fit().placeholder(R.drawable.spinner).into(imageView)
            }
        }
    }

    private fun checkForPermission() {
        val permissionCheck = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        }
    }

    private fun hasPermission(permissionToCheck: String): Boolean {
        val permissionCheck = ContextCompat.checkSelfPermission(this, permissionToCheck)
        return permissionCheck == PackageManager.PERMISSION_GRANTED
    }


    fun getImagesPath(): List<String>? {
        val listOfAllImages: MutableList<String> =
            ArrayList()
        if (hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            val columns =
                arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID)
            val cursor = contentResolver
                .query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    columns,
                    null,
                    null,
                    MediaStore.Images.Media._ID
                )
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        val path =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
                        listOfAllImages.add(path)
                    } while (cursor.moveToNext())
                }
                cursor.close()
            }
        }
        return listOfAllImages
    }
}
