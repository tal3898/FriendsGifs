package com.taban.friendsgifs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.GridView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    companion object {
        val LOG_TAG = "friends_gifs"
    }

    lateinit var gifGridView : GridView
    lateinit var searchingEditView : EditText

    lateinit var imageAdapter: ImageAdapter
    val allGifs: ArrayList<SearchableGif> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gifGridView = gifs_grid
        searchingEditView = searching_edit_text

        allGifs.addAll(Arrays.asList(
                SearchableGif(GifWebView(this, "file:///android_asset/g1.html"), "joe im sorry i'm sorry"),
                SearchableGif(GifWebView(this, "file:///android_asset/g2.html"), "joe how you doin"),
                SearchableGif(GifWebView(this, "file:///android_asset/g3.html"), "ross well done clapping"),
                SearchableGif(GifWebView(this, "file:///android_asset/g4.html"), "rachel phoebe jumping happy"),
                SearchableGif(GifWebView(this, "file:///android_asset/g5.html"), "rachel fuck you curse"),
                SearchableGif(GifWebView(this, "file:///android_asset/g6.html"), "rachel ask me any thing"),
                SearchableGif(GifWebView(this, "file:///android_asset/g7.html"), "rachel hi hey"),
                SearchableGif(GifWebView(this, "file:///android_asset/g8.html"), "ross unagi"),
                SearchableGif(GifWebView(this, "file:///android_asset/g9.html"), "chandler joe thumbs up good for you way to go"),
                SearchableGif(GifWebView(this, "file:///android_asset/g10.html"),"phoebe evil laugh")
                ))

        imageAdapter = ImageAdapter(this, allGifs)
        gifGridView.adapter = imageAdapter

        searchingEditView.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s : Editable) {

            }

            override fun beforeTextChanged(s : CharSequence , start : Int, count : Int, after : Int) {

            }

            override fun onTextChanged(s : CharSequence , start : Int, count : Int, after : Int) {
                if (s.length == 0) {
                    Log.i(LOG_TAG, "The searching is empty, show all")
                    imageAdapter.allGifs = allGifs
                } else {
                    Log.i(LOG_TAG, "The searching text has changed to : " + s.toString())
                    imageAdapter.allGifs = allGifs.filter { it.hasSearchKeyWord(s.toString()) }
                }

                imageAdapter.notifyDataSetChanged()
            }
        })
    }
}
