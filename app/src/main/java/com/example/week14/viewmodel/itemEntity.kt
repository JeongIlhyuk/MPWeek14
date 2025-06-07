package com.example.week14.viewmodel

data class ItemEntity(
    var itemName: String,
    var itemQuantity: Int,
    val itemID: Int
) {
    constructor() : this("noinfo", 0, 0)
}
