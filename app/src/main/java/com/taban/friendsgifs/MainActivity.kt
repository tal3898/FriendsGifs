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
    val allGifs: ArrayList<Int> = ArrayList()


    lateinit var filteredGifs : ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gifGridView = gifs_grid
        searchingEditView = searching_edit_text

        allGifs.addAll(Arrays.asList(R.drawable.a1,
                R.drawable.a2,
                R.drawable.a3,
                R.drawable.a4,
                R.drawable.a5,
                R.drawable.a6,
                R.drawable.a7,
                R.drawable.a8,
                R.drawable.a9,
                R.drawable.a10,
                R.drawable.a11,
                R.drawable.a12,
                R.drawable.a13,
                R.drawable.a14,
                R.drawable.a15,
                R.drawable.a16,
                R.drawable.a17,
                R.drawable.a18))

        filteredGifs = allGifs
        imageAdapter = ImageAdapter(this, filteredGifs)
        gifGridView.adapter = imageAdapter
        searchingEditView.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s : Editable) {

            }

            override fun beforeTextChanged(s : CharSequence , start : Int, count : Int, after : Int) {

            }

            override fun onTextChanged(s : CharSequence , start : Int, count : Int, after : Int) {
                filteredGifs.remove(filteredGifs.get(0))
                imageAdapter.notifyDataSetChanged()
                Log.i(LOG_TAG, "the text has changed, the size " + filteredGifs.size)
            }
        })
    }
}
