package com.taban.friendsgifs

class SearchableGif(_id : Int, _gifResourceId : GifWebView, _searchKeywords : List<String>) {
    var id : Int
    var gifResourceId : GifWebView
    var searchKeywords : List<String>


    init {
        id = _id
        gifResourceId = _gifResourceId
        searchKeywords = _searchKeywords
    }


    /**
     * The method gets a search key words, and returns true if the current
     * gif fits the search key words
     */
    fun hasSearchKeyWord(s : String): Boolean {
        return searchKeywords.any { it.contains(s) }
    }
}