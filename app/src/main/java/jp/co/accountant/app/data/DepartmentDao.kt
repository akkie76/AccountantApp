package jp.co.accountant.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DepartmentDao {

    @Query(
        "SELECT * FROM departments " +
            "WHERE name LIKE '%' || :query || '%' " +
            "OR code LIKE '%' || :query || '%' " +
            "ORDER BY id " +
            "LIMIT :limit"
    )
    fun findDepartmentsByQuery(
        query: String,
        limit: Int
    ): List<Department>

    @Query(
        "SELECT * FROM departments " +
            "WHERE name LIKE '%' || :query || '%' " +
            "ORDER BY id " +
            "LIMIT :limit"
    )
    fun findDepartmentsByQueryWithName(
        query: String,
        limit: Int
    ): List<Department>

    @Query(
        "SELECT * FROM departments " +
            "WHERE code LIKE '%' || :query || '%' " +
            "ORDER BY id " +
            "LIMIT :limit"
    )
    fun findDepartmentsByQueryWithCode(
        query: String,
        limit: Int
    ): List<Department>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(departments: List<Department>)
}
