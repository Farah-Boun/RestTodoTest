package com.bounsiarboughazi.resttodo

data class Todo (
    var id:Int? = 0,
    var title: String="",
    var completed: Boolean = false,
    var userId:Int = 1
)
