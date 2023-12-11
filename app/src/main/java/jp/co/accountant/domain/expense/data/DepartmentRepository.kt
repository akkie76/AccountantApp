package jp.co.accountant.domain.expense.data

import jp.co.accountant.app.data.Department

interface DepartmentRepository {

    suspend fun fetchData(): List<Department>
}
