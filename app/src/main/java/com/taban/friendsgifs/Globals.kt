package com.taban.friendsgifs

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class Globals {

    companion object {
        val LOG_TAG = "friends_gifs"
        val GIFS_COUNT = 21
        val GIF_ID_INTENT_PARAMETER = "GIF_ID"


        fun createSearchableGif(context : Context, id : Int, gifName: String): SearchableGif? {
            var searchKeyWords = getSearchKeywords(context, gifName + ".txt")
            if (searchKeyWords != null) {
                var gifWebView = GifWebView(context, "file:///android_asset/" + gifName + ".html")
                return SearchableGif(id,
                        gifWebView,
                        searchKeyWords.split(","))
            } else {
                return null
            }
        }

        fun getSearchKeywords(context : Context, gifFileName: String): String? {
            var reader: BufferedReader? = null

            try {
                reader = BufferedReader(InputStreamReader(context.assets.open(gifFileName), "UTF-8"))
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

}