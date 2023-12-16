package jp.co.accountant.domain.expense.data

import jp.co.accountant.app.data.DepartmentDao
import jp.co.accountant.app.data.DepartmentWithHistory
import javax.inject.Inject

class DepartmentDataSourceImpl @Inject constructor(
    private val departmentDao: DepartmentDao
) : DepartmentDataSource {

    /**
     * departments tableのname, code columnからLIKE検索を行う
     *
     * @param query 検索クエリ
     * @param limit リミット数
     * @return 利用履歴を含めた部門情報配列
     */
    override suspend fun findDepartmentsByQuery(
        query: String,
        limit: Int
    ): List<DepartmentWithHistory> {
        return departmentDao.findDepartmentsByQuery(
            query = query,
            limit = limit
        )
    }

    /**
     * departments tableのname columnからLIKE検索を行う
     *
     * @param query 検索クエリ
     * @param limit リミット数
     * @return 利用履歴を含めた部門情報配列
     */
    override suspend fun findDepartmentsByQueryWithName(
        query: String,
        limit: Int
    ): List<DepartmentWithHistory> {
        return departmentDao.findDepartmentsByQueryWithName(
            query = query,
            limit = limit
        )
    }

    /**
     * departments tableのcode columnからLIKE検索を行う
     *
     * @param query 検索クエリ
     * @param limit リミット数
     * @return 利用履歴を含めた部門情報配列
     */
    override suspend fun findDepartmentsByQueryWithCode(
        query: String,
        limit: Int
    ): List<DepartmentWithHistory> {
        return departmentDao.findDepartmentsByQueryWithCode(
            query = query,
            limit = limit
        )
    }
}
