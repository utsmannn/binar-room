package com.utsman.binarroom

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Query("select * from User")
    fun getAllUser(): List<User>

    @Query("select * from User where id=:id")
    fun getUserById(id: Int): User?

    @Insert(onConflict = REPLACE)
    fun addUser(user: User): Long

    @Update
    fun updateUser(user: User): Int

    @Delete
    fun deleteUser(user: User): Int
}