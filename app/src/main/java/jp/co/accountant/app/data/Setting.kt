package jp.co.accountant.app.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class Setting(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "is_configured")
    val isConfigured: Boolean
) {
    companion object {
        val INITIAL = Setting(1, true)
    }
}
