package com.taban.friendsgifs

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.GridView
import com.taban.friendsgifs.Globals.Companion.GIFS_COUNT
import com.taban.friendsgifs.Globals.Companion.GIF_ID_INTENT_PARAMETER
import com.taban.friendsgifs.Globals.Companion.LOG_TAG
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var gifGridView: GridView
    lateinit var searchingEditView: EditText

    lateinit var imageAdapter: ImageAdapter
    val allGifs: ArrayList<SearchableGif> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gifGridView = gifs_grid
        searchingEditView = searching_edit_text

        loadAllGifs()

        imageAdapter = ImageAdapter(this, allGifs)
        gifGridView.adapter = imageAdapter
        gifGridView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                // Get the GridView selected/clicked item text
                val selectedGif = parent.getItemAtPosition(position) as SearchableGif
                Log.i(LOG_TAG, "clicked " + position)

                var intent = Intent(this@MainActivity, SpecificGifActivity::class.java)
                Log.i(LOG_TAG, "puting extra with gif id " + selectedGif.id)
                intent.putExtra(GIF_ID_INTENT_PARAMETER, selectedGif.id)
                startActivity(intent)
            }
        }

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

    fun loadAllGifs() {
        for (x in 1..GIFS_COUNT) {
            var gif = createSearchableGif(x, "g" + x)
            if (gif != null) {
                allGifs.add(gif)
            } else {
                Log.e(LOG_TAG, "could not load the gif g" + x)
            }
        }
    }

    fun createSearchableGif(id : Int, gifName: String): SearchableGif? {
        var searchKeyWords = getSearchKeywords(gifName + ".txt")
        if (searchKeyWords != null) {
            var gifWebView = GifWebView(this, "file:///android_asset/" + gifName + ".html")
            return SearchableGif(id,
                    gifWebView,
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
