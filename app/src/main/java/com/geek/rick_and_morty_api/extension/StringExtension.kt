package com.geek.rick_and_morty_api.extension


val String.id: String?
    get() {
        val index = this.indexOf("r/")
        return if (index == -1) null else this.substring(index + 2)
    }

fun List<String>.mapTo(): String {
    var str = ""
    this.forEach {
        str += "${it.id},"
    }

    return str
}