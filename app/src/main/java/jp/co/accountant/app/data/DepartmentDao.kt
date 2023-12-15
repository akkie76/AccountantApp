package jp.co.accountant.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DepartmentDao {

    @Query("SELECT * FROM departments WHERE id > :startAfterId ORDER BY id LIMIT :limit")
    fun findDepartmentsAfterId(startAfterId: Int, limit: Int): List<Department>

    @Query("SELECT * FROM departments WHERE name LIKE '%' || :query || '%' AND id > :startAfterId ORDER BY id LIMIT :limit")
    fun findDepartmentsByQuery(query: String, startAfterId: Int, limit: Int): List<Department>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(departments: List<Department>)
}
