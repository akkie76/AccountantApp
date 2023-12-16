package jp.co.accountant.domain.expense.data

import jp.co.accountant.app.data.Department

interface DepartmentDataSource {

    suspend fun findDepartmentsByQuery(
        query: String,
        limit: Int
    ): List<Department>

    suspend fun findDepartmentsByQueryWithName(
        query: String,
        limit: Int
    ): List<Department>

    suspend fun findDepartmentsByQueryWithCode(
        query: String,
        limit: Int
    ): List<Department>
}
