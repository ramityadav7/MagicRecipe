package com.simpragma.magicrecipe.network.model

class Result {
    var title: String? = null
    var href: String? = null
    var ingredients: String? = null
    var thumbnail: String? = null

    override fun equals(other: Any?): Boolean {
        return this.title == (other as Result).title
    }
}