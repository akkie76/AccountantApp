package jp.co.accountant.app.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "departments")
data class Department(
    @PrimaryKey
    val id: Int,
    val name: String,
    val code: String,
    @Embedded
    val hasHistory: Boolean
)
