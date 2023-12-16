package jp.co.accountant.app.data

import androidx.room.Embedded

data class DepartmentWithHistory(
    @Embedded val department: Department
)
