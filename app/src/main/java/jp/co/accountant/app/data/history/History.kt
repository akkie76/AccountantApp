package jp.co.accountant.app.data.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "histories", indices = [Index(value = ["department_id"], unique = true)])
data class History(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "department_id")
    val departmentId: Int
)
