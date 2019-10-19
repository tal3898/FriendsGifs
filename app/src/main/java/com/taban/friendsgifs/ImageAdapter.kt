package com.taban.friendsgifs

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView

class ImageAdapter(_context : Context) : BaseAdapter() {

    val context : Context

    init {
        context = _context
    }

    val allGifs: IntArray = intArrayOf(R.drawable.a1,
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
            R.drawable.a18)

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