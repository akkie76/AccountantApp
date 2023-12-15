package jp.co.accountant.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "departments")
data class Department(
    @PrimaryKey
    val id: Int,
    val name: String
)
