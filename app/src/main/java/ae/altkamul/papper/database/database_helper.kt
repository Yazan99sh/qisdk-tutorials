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
    }

    // This method is called when the database needs to be upgraded
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        dropUsersTable(db)
        onCreate(db)
    }

    private fun dropUsersTable(db: SQLiteDatabase) {
        val TABLE_NAME = DatabaseTableConstant.Users.TABLE_NAME
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }

    fun createUsersTable(db: SQLiteDatabase) {
        val TABLE_NAME = DatabaseTableConstant.Users.TABLE_NAME
        val COLUMN_ID = DatabaseTableConstant.Users.COLUMN_ID
        val COLUMN_NAME = DatabaseTableConstant.Users.COLUMN_ID
        val COLUMN_PHONE = DatabaseTableConstant.Users.COLUMN_ID
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_PHONE TEXT
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }
}
