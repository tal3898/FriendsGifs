package com.taban.friendsgifs

class SearchableGif(_gifResourceId : GifWebView, _searchKeywords : String) {
    var gifResourceId : GifWebView
    var searchKeywords : String

    init {
        gifResourceId = _gifResourceId
        searchKeywords = _searchKeywords
    }

    constructor(_gifResourceId: GifWebView) : this(_gifResourceId, ""){

    }

    fun addKeyword(s : String) : SearchableGif{
        searchKeywords = searchKeywords + " " + s
        return this
    }

    /**
     * The method gets a search key words, and returns true if the current
     * gif fits the search key words
     */
    fun hasSearchKeyWord(s : String): Boolean {
        return searchKeywords.contains(s)
    }
}