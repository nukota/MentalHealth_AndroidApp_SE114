package com.example.uplift.logic.repository

import androidx.lifecycle.LiveData
import com.example.uplift.data.models.User
import com.example.uplift.logic.dao.UserDao
import com.example.uplift.data.database.UserDatabase

class UserRepository(private val userDao: UserDao) {
    val getAllUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

    suspend fun verifyUser(username: String, password: String): Boolean {
        val user = userDao.getUserByUsername(username)
        return user?.password == password
    }
}