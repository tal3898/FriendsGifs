package com.taban.friendsgifs.activities

import android.content.Context
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
import com.taban.friendsgifs.utility.Globals.Companion.GIFS_COUNT
import com.taban.friendsgifs.utility.Globals.Companion.GIF_ID_INTENT_PARAMETER
import com.taban.friendsgifs.utility.Globals.Companion.LOG_TAG
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList
import com.taban.friendsgifs.*
import com.taban.friendsgifs.models.ImageAdapter
import com.taban.friendsgifs.models.SearchableGif
import com.taban.friendsgifs.utility.Globals


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
                Log.i(LOG_TAG, "puting extra with gif id " + selectedGif.resourcId)
                intent.putExtra(GIF_ID_INTENT_PARAMETER, selectedGif.resourcId)
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
            var gif = createSearchableGif(this, x, "g" + x)
            if (gif != null) {
                allGifs.add(gif)
            } else {
                Log.e(LOG_TAG, "could not load the gif g" + x)
            }
        }
    }

    fun createSearchableGif(context : Context, id : Int, gifName: String): SearchableGif? {
        var searchKeyWords = Globals.getSearchKeywords(context, gifName + ".txt")
        if (searchKeyWords != null) {
            val resID = resources.getIdentifier(gifName,
                    "raw", packageName)
            return SearchableGif(resID,
                    searchKeyWords.split(","))
        } else {
            return null
        }
    }

}
