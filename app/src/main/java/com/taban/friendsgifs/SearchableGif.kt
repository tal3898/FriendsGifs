package com.taban.friendsgifs

class SearchableGif(_gifResourceId : Int, _searchKeywords : HashSet<String>) {
    var gifResourceId : Int
    var searchKeywords : HashSet<String>

    init {
        gifResourceId = _gifResourceId
        searchKeywords = _searchKeywords
    }

    constructor(_gifResourceId: Int) : this(_gifResourceId, HashSet<String>()){

    }

    fun addKeyword(s : String) {
        searchKeywords.add(s)
    }

    /**
     * The method gets a search key words, and returns true if the current
     * gif fits the search key words
     */
    fun hasSearchKeyWord(s : String): Boolean {
        return searchKeywords.any { it.contains(s) }
    }
}