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
    interface Winners {
        companion object {
            const val TABLE_NAME = "winners"
            const val COLUMN_ID = "id"
            const val COLUMN_USER_ID = "userId"
            const val COLUMN_NAME = "name"
            const val COLUMN_RANK = "rank"
            const val COLUMN_DATE = "date"
        }
    }
}