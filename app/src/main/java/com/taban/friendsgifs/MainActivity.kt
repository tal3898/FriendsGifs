package com.taban.friendsgifs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.GridView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    companion object {
        val LOG_TAG = "friends_gifs"
    }

    lateinit var gifGridView: GridView
    lateinit var searchingEditView: EditText

    lateinit var imageAdapter: ImageAdapter
    val allGifs: ArrayList<SearchableGif> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gifGridView = gifs_grid
        searchingEditView = searching_edit_text

        allGifs.addAll(Arrays.asList(
                createSearchableGif("g1")!!,
                createSearchableGif("g2")!!,
                createSearchableGif("g3")!!,
                createSearchableGif("g4")!!,
                createSearchableGif("g5")!!,
                createSearchableGif("g6")!!,
                createSearchableGif("g7")!!,
                createSearchableGif("g8")!!,
                createSearchableGif("g9")!!,
                createSearchableGif("g10")!!
        ))


        imageAdapter = ImageAdapter(this, allGifs)
        gifGridView.adapter = imageAdapter

        searchingEditView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
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


    fun createSearchableGif(gifName: String): SearchableGif? {
        var searchKeyWords = getSearchKeywords(gifName + ".txt")
        if (searchKeyWords != null) {
            return SearchableGif(GifWebView(this, "file:///android_asset/" + gifName + ".html"),
                    searchKeyWords.split(","))
        } else {
            return null
        }
    }

    fun getSearchKeywords(gifFileName: String): String? {
        var reader: BufferedReader? = null

        try {
            reader = BufferedReader(InputStreamReader(getAssets().open(gifFileName), "UTF-8"))
            var mLine = reader.readLine()
            return mLine
        } catch (e: Exception) {
            Log.e(LOG_TAG, "could not read the file", e)
            return null
        } finally {
            if (reader != null) {
                try {
                    reader?.close()
                } catch (e: IOException) {
                    Log.e(LOG_TAG, "could not close file")
                }
            }
        }
    }
}
