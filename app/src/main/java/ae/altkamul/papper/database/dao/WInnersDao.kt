package ae.altkamul.papper.database.dao

import ae.altkamul.papper.database.DatabaseHelper
import ae.altkamul.papper.database.constant.DatabaseTableConstant
import ae.altkamul.papper.database.data_model.User
import ae.altkamul.papper.database.data_model.Winner
import android.content.Context

class WinnersDao(context: Context) {
    private val dbHelper = DatabaseHelper(context)
    fun insertWinner(winner: Winner): Boolean {
        val db = dbHelper.writableDatabase
        val values = winner.toContentValues()
        return db.insert(DatabaseTableConstant.Winners.TABLE_NAME, null, values) >= 0
    }

    fun getWinners(): List<Winner> {
        val db = dbHelper.readableDatabase
        val winners = mutableListOf<Winner>()
        val cursor = db.rawQuery(
            "SELECT * FROM ${DatabaseTableConstant.Users.TABLE_NAME}",
            null
        )
        while (cursor.moveToNext()) {
            val id =
                cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseTableConstant.Winners.COLUMN_ID))
            val userID =
                cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseTableConstant.Winners.COLUMN_USER_ID))
            val name =
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTableConstant.Winners.COLUMN_NAME))
            val rank =
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTableConstant.Winners.COLUMN_RANK))
            val date =
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTableConstant.Winners.COLUMN_DATE))
            winners.add(
                Winner(
                    id,
                    userID,
                    name,
                    rank,
                    date.toString()
                )
            )
        }
        cursor.close()
        return winners
    }

}