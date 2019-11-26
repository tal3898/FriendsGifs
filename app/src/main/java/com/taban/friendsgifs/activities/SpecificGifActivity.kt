package com.taban.friendsgifs.activities

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.taban.friendsgifs.views.GifImageView
import com.taban.friendsgifs.utility.Globals.Companion.GIF_ID_INTENT_PARAMETER
import com.taban.friendsgifs.utility.Globals.Companion.LOG_TAG
import com.taban.friendsgifs.R

class SpecificGifActivity : AppCompatActivity() {

    var selectedGifResourceId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_gif)

        selectedGifResourceId = intent.getIntExtra(GIF_ID_INTENT_PARAMETER,-1)
        Log.i(LOG_TAG, "created with gif id " + selectedGifResourceId)

        // Display the selected gif
        var gifImageView = findViewById(R.id.gif) as GifImageView
        gifImageView.setGifResource(selectedGifResourceId)
    }

    fun onShareButtonClick(view : View) {
        val sharingIntent = Intent(Intent.ACTION_SEND).apply {
            setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://" + packageName + "/" + R.raw.mini))
            type="image/jpg"
        }
        startActivity(Intent.createChooser(sharingIntent, "Share via"))
    }


}
