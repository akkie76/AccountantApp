package jp.co.accountant.domain.expense.data

import jp.co.accountant.app.data.Department

interface DepartmentDataSource {

    suspend fun findDepartmentsAfterId(startAfterId: Int, limit: Int): List<Department>

    suspend fun findDepartmentsByQuery(query: String, startAfterId: Int, limit: Int): List<Department>
}
