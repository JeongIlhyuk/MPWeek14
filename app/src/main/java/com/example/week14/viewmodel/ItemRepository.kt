package com.example.week14.viewmodel

import com.example.week14.roomDB.ItemDatabase

class ItemRepository(private val db: ItemDatabase) {
    val dao = db.getItemDao()

    suspend fun InsertItem(itemEntity: ItemEntity) {
        dao.InsertItem(itemEntity)
    }

    suspend fun UpdateItem(itemEntity: ItemEntity) {
        dao.UpdateItem(itemEntity)
    }

    suspend fun DeleteItem(itemEntity: ItemEntity) {
        dao.DeleteItem(itemEntity)
    }

    fun getAllItems() = dao.getAllItems()

    fun getItems(itemName: String) = dao.getItems(itemName)
}