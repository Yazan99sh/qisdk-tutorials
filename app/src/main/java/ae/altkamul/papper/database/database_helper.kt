package ae.altkamul.papper.database

import ae.altkamul.papper.database.constant.DatabaseTableConstant
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "papper.db"
        const val DATABASE_VERSION = 1
    }

    // This method is called when the database is created for the first time
    override fun onCreate(db: SQLiteDatabase) {
        createUsersTable(db)
        createWinnerTable(db)
    }

    // This method is called when the database needs to be upgraded
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        dropUsersTable(db)
        dropWinnerTable(db)
        onCreate(db)
    }

    private fun dropUsersTable(db: SQLiteDatabase) {
        val TABLE_NAME = DatabaseTableConstant.Users.TABLE_NAME
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }

    private fun dropWinnerTable(db: SQLiteDatabase) {
        val TABLE_NAME = DatabaseTableConstant.Winners.TABLE_NAME
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }

    fun createUsersTable(db: SQLiteDatabase) {
        val TABLE_NAME = DatabaseTableConstant.Users.TABLE_NAME
        val COLUMN_ID = DatabaseTableConstant.Users.COLUMN_ID
        val COLUMN_NAME = DatabaseTableConstant.Users.COLUMN_NAME
        val COLUMN_PHONE = DatabaseTableConstant.Users.COLUMN_PHONE
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_PHONE TEXT
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    fun createWinnerTable(db: SQLiteDatabase) {
        val TABLE_NAME = DatabaseTableConstant.Winners.TABLE_NAME
        val COLUMN_ID = DatabaseTableConstant.Winners.COLUMN_ID
        val COLUMN_USER_ID = DatabaseTableConstant.Winners.COLUMN_USER_ID
        val COLUMN_NAME = DatabaseTableConstant.Winners.COLUMN_NAME
        val COLUMN_RANK = DatabaseTableConstant.Winners.COLUMN_RANK
        val COLUMN_DATE = DatabaseTableConstant.Winners.COLUMN_DATE
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USER_ID INTEGER,
                $COLUMN_NAME TEXT,
                $COLUMN_RANK TEXT,
                $COLUMN_DATE TEXT,
                FOREIGN KEY($COLUMN_USER_ID) REFERENCES ${DatabaseTableConstant.Users.TABLE_NAME}(${DatabaseTableConstant.Users.COLUMN_ID})
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }
}
