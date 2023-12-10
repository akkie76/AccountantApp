package jp.co.accountant.domain.expense.data

interface DepartmentRepository {

    suspend fun fetchData(): List<Department>
}
