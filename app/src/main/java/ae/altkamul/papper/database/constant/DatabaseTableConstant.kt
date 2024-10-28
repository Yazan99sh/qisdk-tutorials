package ae.altkamul.papper.database.constant

interface DatabaseTableConstant {
    interface Users {
        companion object {
            const val TABLE_NAME = "users"
            const val COLUMN_ID = "id"
            const val COLUMN_NAME = "name"
            const val COLUMN_PHONE = "phone"
        }
    }
}