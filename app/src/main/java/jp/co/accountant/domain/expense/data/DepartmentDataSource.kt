package jp.co.accountant.domain.expense.data

import jp.co.accountant.app.data.DepartmentWithHistory

interface DepartmentDataSource {

    /**
     * departments tableのname, code columnからLIKE検索を行う
     *
     * @param query 検索クエリ
     * @param limit リミット数
     * @return 利用履歴を含めた部門情報配列
     */
    suspend fun findDepartmentsByQuery(
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
    suspend fun findDepartmentsByQueryWithName(
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
    suspend fun findDepartmentsByQueryWithCode(
        query: String,
        limit: Int
    ): List<DepartmentWithHistory>
}
