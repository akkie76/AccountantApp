package jp.co.accountant.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DepartmentDao {

    /**
     * departments tableのname, code columnからLIKE検索を行う
     *
     * @param query 検索クエリ
     * @param limit リミット数
     */
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

    /**
     * departments tableのname columnからLIKE検索を行う
     *
     * @param query 検索クエリ
     * @param limit リミット数
     */
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

    /**
     * departments tableのcode columnからLIKE検索を行う
     *
     * @param query 検索クエリ
     * @param limit リミット数
     */
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

    /**
     * departments tableに部門一覧をinsertする
     *
     * @param departments 部門一覧
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(departments: List<Department>)
}
