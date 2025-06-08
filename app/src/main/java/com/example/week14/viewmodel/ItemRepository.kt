package com.example.week14.viewmodel

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class ItemRepository(private val table: DatabaseReference) {


    suspend fun InsertItem(itemEntity: ItemEntity) {
//        table.
//        child(itemEntity.itemID.toString())
//            .setValue(itemEntity)
//            .addOnCompleteListener {
//                if(it.isSuccessful)
//                    Log.i("insert", "성공")
//                else
//                    Log.i("insert", "실패")
//            }
        try {

            table.child(itemEntity.itemID.toString())
                .setValue(itemEntity)
                .await()
            Log.i("insert", "성공")
        } catch (e: Exception) {
            Log.i("insert", "실패")
        }
    }

    suspend fun UpdateItem(itemEntity: ItemEntity) {
        try {
            table.child(itemEntity.itemID.toString())
                .child("itemQuantity")
                .setValue(itemEntity.itemQuantity)
                .await()
            Log.i("update", "성공")
        } catch (e: Exception) {
            Log.i("update", "실패")
        }
    }

    suspend fun DeleteItem(itemEntity: ItemEntity) {
        try {
            table.child(itemEntity.itemID.toString())
                .removeValue()
                .await()
            Log.i("delete", "성공")
        } catch (e: Exception) {
            Log.i("delete", "실패")
        }
    }

    fun getAllItems(): Flow<List<ItemEntity>> {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val itemList = snapshot.children.mapNotNull {
                    it.getValue(ItemEntity::class.java)
                }
                trySend(itemList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
    }

//    fun getAllItems() = dao.getAllItems()
//
//    fun getItems(itemName: String) = dao.getItems(itemName)
}