package jp.co.accountant.app.data.department

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class DepartmentWithHistory(
    @Embedded val department: Department,
    @ColumnInfo(name = "has_history")
    val hasHistory: Boolean
)
