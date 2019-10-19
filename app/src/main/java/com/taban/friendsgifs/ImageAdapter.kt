package com.taban.friendsgifs

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView

class ImageAdapter(_context : Context, _gifsArray : List<Int>) : BaseAdapter() {

    val allGifs: List<Int>
    val context : Context

    init {
        context = _context
        allGifs = _gifsArray
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var imageView = ImageView(context)
        imageView.setImageResource(allGifs[position])
        imageView.scaleType = ImageView.ScaleType.CENTER
        imageView.layoutParams = ViewGroup.LayoutParams(450, 350)

        return imageView
    }

    override fun getItem(position: Int): Any {
        return allGifs[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return allGifs.count()
    }

}