package com.taban.friendsgifs.models

class SearchableGif(_resourceId : Int, _searchKeywords : List<String>) {
    var resourcId : Int
    var searchKeywords : List<String>


    init {
        resourcId = _resourceId
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