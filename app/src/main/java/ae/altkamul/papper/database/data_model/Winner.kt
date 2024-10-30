package ae.altkamul.papper.database.data_model

import ae.altkamul.papper.database.constant.DatabaseTableConstant
import android.content.ContentValues

data class Winner(
    val id: Int?,
    val userId: Int,
    val name: String,
    val rank: String,
    val date: String
) {
    fun toContentValues(): ContentValues {
        val values = ContentValues()
        values.put(DatabaseTableConstant.Winners.COLUMN_USER_ID, name)
        values.put(DatabaseTableConstant.Winners.COLUMN_NAME, name)
        values.put(DatabaseTableConstant.Winners.COLUMN_RANK, name)
        values.put(DatabaseTableConstant.Winners.COLUMN_DATE, name)
        return values
    }
}
