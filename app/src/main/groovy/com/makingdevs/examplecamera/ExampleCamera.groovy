package com.makingdevs.examplecamera

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v7.app.ActionBarActivity
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import groovy.transform.CompileStatic

@CompileStatic
class ExampleCamera extends ActionBarActivity {

    ImageButton cameraImageButton
    static final int CAPTURE_IMAGE = 1
    final String TAG = "ExampleCamera"
    File filePhoto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_camera)
        bindingViews()
    }

    void bindingViews() {
        cameraImageButton = (ImageButton) findViewById(R.id.imageButtonCamera)
        cameraImageButton.onClickListener = {
            launchCamera()
        }
    }

    void launchCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (camera.resolveActivity(getPackageManager())) {
            try {
                filePhoto = createPhoto("IMG_")
            } catch (IOException ex) {
                Log.d(TAG, "Error ${ex.message}")
            }
            if (filePhoto) {
                camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filePhoto))
                startActivityForResult(camera, CAPTURE_IMAGE)
            }
        }
    }

    File createPhoto(String name) {
        File storagePhotos = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "ExampleCamera")
        if (!storagePhotos.exists()) {
            if (!storagePhotos.mkdirs()) {
                Log.d(TAG, "Error al crear directorio")
            }
        }
        new File(storagePhotos.getPath() + File.separator + "${name + new Date().format("ddMMyyyy_HHmmss")}.jpg")
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAPTURE_IMAGE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Exito al crear la foto", Toast.LENGTH_SHORT).show()
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Se cancelo la foto", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Error al capturar la foto", Toast.LENGTH_LONG).show()
            }
        }
    }
}
