package ae.altkamul.papper.database.data_model

import ae.altkamul.papper.database.constant.DatabaseTableConstant
import android.content.ContentValues

data class User(val name: String, val phone: String) {
    fun toContentValues(): ContentValues {
        val values = ContentValues()
        values.put(DatabaseTableConstant.Users.COLUMN_NAME, name)
        values.put(DatabaseTableConstant.Users.COLUMN_PHONE, phone)
        return values
    }
}
