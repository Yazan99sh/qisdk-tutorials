package ae.altkamul.papper.database.data_model

import ae.altkamul.papper.database.constant.DatabaseTableConstant
import android.content.ContentValues

data class Winner(
    val id: Int?,
    val userId: Int,
    val name: String,
    val rank: String,
    val date: String,
    val isOnline: Boolean
) {
    fun toContentValues(): ContentValues {
        val values = ContentValues()
        values.put(DatabaseTableConstant.Winners.COLUMN_USER_ID, userId)
        values.put(DatabaseTableConstant.Winners.COLUMN_NAME, name)
        values.put(DatabaseTableConstant.Winners.COLUMN_RANK, rank)
        values.put(DatabaseTableConstant.Winners.COLUMN_DATE, date)
        values.put(DatabaseTableConstant.Winners.COLUMN_IS_ONLINE, isOnline)
        return values
    }
}
