package jp.co.accountant.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DepartmentDao {

    @Query("SELECT * FROM department WHERE id > :startAfterId ORDER BY id LIMIT :limit")
    fun findDepartmentsAfterId(startAfterId: Int, limit: Int): List<Department>

    @Query("SELECT * FROM department WHERE name LIKE '%' || :query || '%' ORDER BY id")
    fun findDepartmentsByName(query: String): List<Department>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(departments: List<Department>)
}
