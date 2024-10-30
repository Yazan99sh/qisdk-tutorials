package ae.altkamul.papper.database.dao

import ae.altkamul.papper.database.DatabaseHelper
import ae.altkamul.papper.database.constant.DatabaseTableConstant
import ae.altkamul.papper.database.data_model.User
import android.content.Context

class UsersDao(context: Context) {
    private val dbHelper = DatabaseHelper(context)
    fun insertUser(user: User): Boolean {
        val db = dbHelper.writableDatabase
        val values = user.toContentValues()
        return db.insert(DatabaseTableConstant.Users.TABLE_NAME, null, values) >= 0
    }

    fun getUsers(): List<User> {
        val db = dbHelper.readableDatabase
        val users = mutableListOf<User>()
        val cursor = db.rawQuery(
            "SELECT * FROM ${DatabaseTableConstant.Users.TABLE_NAME}",
            null
        )
        while (cursor.moveToNext()) {
            val id =
                cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseTableConstant.Users.COLUMN_ID))
            val name =
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTableConstant.Users.COLUMN_NAME))
            val phone =
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTableConstant.Users.COLUMN_PHONE))
            users.add(User(id, name, phone))
        }
        cursor.close()
        return users
    }

}