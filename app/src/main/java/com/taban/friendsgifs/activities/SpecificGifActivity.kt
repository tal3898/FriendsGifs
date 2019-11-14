package com.taban.friendsgifs.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.taban.friendsgifs.views.GifImageView
import com.taban.friendsgifs.utility.Globals.Companion.GIF_ID_INTENT_PARAMETER
import com.taban.friendsgifs.utility.Globals.Companion.LOG_TAG
import com.taban.friendsgifs.R

class SpecificGifActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_gif)

        val gifResourceId = intent.getIntExtra(GIF_ID_INTENT_PARAMETER,-1)
        Log.i(LOG_TAG, "created with gif id " + gifResourceId)
        var gifImageView = findViewById(R.id.gif) as GifImageView
        gifImageView.setGifResource(gifResourceId)
    }

    fun onShareButtonClick(view : View) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        val shareBody = "Here is the share content body"
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here")
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(sharingIntent, "Share via"))
    }
}
