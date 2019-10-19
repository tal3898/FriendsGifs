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
                SearchableGif(R.drawable.a1).addKeyword("1"),
                SearchableGif(R.drawable.a2).addKeyword("2"),
                SearchableGif(R.drawable.a3).addKeyword("3"),
                SearchableGif(R.drawable.a4).addKeyword("4"),
                SearchableGif(R.drawable.a5).addKeyword("5"),
                SearchableGif(R.drawable.a6).addKeyword("6"),
                SearchableGif(R.drawable.a7).addKeyword("7"),
                SearchableGif(R.drawable.a8).addKeyword("8"),
                SearchableGif(R.drawable.a9).addKeyword("9"),
                SearchableGif(R.drawable.a10).addKeyword("10").addKeyword("0"),
                SearchableGif(R.drawable.a11).addKeyword("11").addKeyword("1"),
                SearchableGif(R.drawable.a12).addKeyword("12").addKeyword("2"),
                SearchableGif(R.drawable.a13).addKeyword("13").addKeyword("3"),
                SearchableGif(R.drawable.a14).addKeyword("14").addKeyword("4"),
                SearchableGif(R.drawable.a15).addKeyword("15").addKeyword("5"),
                SearchableGif(R.drawable.a16).addKeyword("16").addKeyword("6"),
                SearchableGif(R.drawable.a17).addKeyword("17").addKeyword("7"),
                SearchableGif(R.drawable.a18).addKeyword("18").addKeyword("8")
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
