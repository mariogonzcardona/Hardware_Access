package org.bedu.hardware_access

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider


class MainActivity : AppCompatActivity() {

    val PERMISSION_ID = 34
    private lateinit var btnCamara:Button
    private lateinit var imgView:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCamara=findViewById(R.id.btnTakePicture)
        imgView=findViewById(R.id.imageView)

        btnCamara.setOnClickListener(View.OnClickListener {
            val takePictureIntent = Intent( MediaStore.ACTION_IMAGE_CAPTURE)
            if(takePictureIntent.resolveActivity(this.packageManager) != null){
                startActivityForResult(takePictureIntent,PERMISSION_ID)
            }
            else{
                Toast.makeText(this,"Unable to Open Camera",Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==PERMISSION_ID && resultCode== Activity.RESULT_OK){
            val takeImage=data?.extras?.get("data") as Bitmap
            imgView.setImageBitmap(takeImage)
        }
        else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}