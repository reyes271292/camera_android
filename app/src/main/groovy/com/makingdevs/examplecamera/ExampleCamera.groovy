package com.makingdevs.examplecamera;

import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.widget.ImageButton

class ExampleCamera extends ActionBarActivity {

    ImageButton cameraImageButton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_camera)
        bindingViews()
    }

    void bindingViews(){
        cameraImageButton = (ImageButton) findViewById(R.id.imageButtonCamera)
        cameraImageButton.onClickListener = {
            println("Camera...")
        }
    }
}
