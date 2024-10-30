package ae.altkamul.papper.model.repository

import ae.altkamul.papper.database.dao.UsersDao
import ae.altkamul.papper.database.data_model.User
import android.content.Context

class RegistrationLocalRepository private constructor(context: Context) {

    // Initialize your DAO or database-related objects here
    private val usersDao: UsersDao = UsersDao(context)

    // Singleton instance
    companion object {
        @Volatile
        private var instance: RegistrationLocalRepository? = null

        fun getInstance(context: Context): RegistrationLocalRepository {
            return instance ?: synchronized(this) {
                instance ?: RegistrationLocalRepository(context).also { instance = it }
            }
        }
    }

    fun registerUser(user: User): Boolean {
        return usersDao.insertUser(user)
    }

    fun getUsers(): List<User> {
        return usersDao.getUsers()
    }
}
