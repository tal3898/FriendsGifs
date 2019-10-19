package com.taban.friendsgifs

class SearchableGif(_gifResourceId : GifWebView, _searchKeywords : HashSet<String>) {
    var gifResourceId : GifWebView
    var searchKeywords : HashSet<String>

    init {
        gifResourceId = _gifResourceId
        searchKeywords = _searchKeywords
    }

    constructor(_gifResourceId: GifWebView) : this(_gifResourceId, HashSet<String>()){

    }

    fun addKeyword(s : String) : SearchableGif{
        searchKeywords.add(s)
        return this
    }

    /**
     * The method gets a search key words, and returns true if the current
     * gif fits the search key words
     */
    fun hasSearchKeyWord(s : String): Boolean {
        return searchKeywords.any { it.contains(s) }
    }
}