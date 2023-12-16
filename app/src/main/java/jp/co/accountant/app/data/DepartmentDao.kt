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
     * @return 利用履歴を含めた部門情報配列
     */
    @Query(
        "SELECT departments.*, (histories.id IS NOT NULL) AS has_history " +
            "FROM departments " +
            "LEFT JOIN histories ON departments.id = histories.department_id  " +
            "WHERE name LIKE '%' || :query || '%' " +
            "OR code LIKE '%' || :query || '%' " +
            "ORDER BY has_history DESC, id ASC " +
            "LIMIT :limit"
    )
    fun findDepartmentsByQuery(
        query: String,
        limit: Int
    ): List<DepartmentWithHistory>

    /**
     * departments tableのname columnからLIKE検索を行う
     *
     * @param query 検索クエリ
     * @param limit リミット数
     * @return 利用履歴を含めた部門情報配列
     */
    @Query(
        "SELECT departments.*, (histories.id IS NOT NULL) AS has_history " +
            "FROM departments " +
            "LEFT JOIN histories ON departments.id = histories.department_id  " +
            "WHERE name LIKE '%' || :query || '%' " +
            "ORDER BY has_history DESC, id ASC " +
            "LIMIT :limit"
    )
    fun findDepartmentsByQueryWithName(
        query: String,
        limit: Int
    ): List<DepartmentWithHistory>

    /**
     * departments tableのcode columnからLIKE検索を行う
     *
     * @param query 検索クエリ
     * @param limit リミット数
     * @return 利用履歴を含めた部門情報配列
     */
    @Query(
        "SELECT departments.*, (histories.id IS NOT NULL) AS has_history " +
            "FROM departments " +
            "LEFT JOIN histories ON departments.id = histories.department_id  " +
            "WHERE code LIKE '%' || :query || '%' " +
            "ORDER BY has_history DESC, id ASC " +
            "LIMIT :limit"
    )
    fun findDepartmentsByQueryWithCode(
        query: String,
        limit: Int
    ): List<DepartmentWithHistory>

    /**
     * departments tableに部門一覧をinsertする
     *
     * @param departments 部門一覧
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(departments: List<Department>)
}
