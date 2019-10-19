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
                SearchableGif(GifWebView(this, "file:///android_asset/g1.html")).addKeyword("joe").addKeyword("im sorry").addKeyword("i'm sorry"),
                SearchableGif(GifWebView(this, "file:///android_asset/g2.html")).addKeyword("joe").addKeyword("how you doin"),
                SearchableGif(GifWebView(this, "file:///android_asset/g3.html")).addKeyword("ross").addKeyword("well done").addKeyword("clapping"),
                SearchableGif(GifWebView(this, "file:///android_asset/g4.html")).addKeyword("rachel").addKeyword("phoebe").addKeyword("jumping").addKeyword("happy"),
                SearchableGif(GifWebView(this, "file:///android_asset/g5.html")).addKeyword("rachel").addKeyword("fuck you").addKeyword("curse"),
                SearchableGif(GifWebView(this, "file:///android_asset/g6.html")).addKeyword("rachel").addKeyword("ask me any thing"),
                SearchableGif(GifWebView(this, "file:///android_asset/g7.html")).addKeyword("rachel").addKeyword("hi").addKeyword("hey"),
                SearchableGif(GifWebView(this, "file:///android_asset/g8.html")).addKeyword("ross").addKeyword("unagi"),
                SearchableGif(GifWebView(this, "file:///android_asset/g9.html")).addKeyword("chandler").addKeyword("joe").addKeyword("thumb up").addKeyword("good for you").addKeyword("way to go"),
                SearchableGif(GifWebView(this, "file:///android_asset/g10.html")).addKeyword("phoebe").addKeyword("evil laugh")
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
