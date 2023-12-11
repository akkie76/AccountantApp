package jp.co.accountant.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DepartmentDao {

    @Query("SELECT * FROM department WHERE name LIKE '%' || :query || '%' ORDER BY id")
    fun getDepartments(query: String = "Mi"): List<Department>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(departments: List<Department>)
}
