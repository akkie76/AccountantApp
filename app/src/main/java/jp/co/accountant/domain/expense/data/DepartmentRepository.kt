package jp.co.accountant.domain.expense.data

import androidx.paging.PagingData
import jp.co.accountant.app.data.Department
import kotlinx.coroutines.flow.Flow

interface DepartmentRepository {

    fun fetchDepartments(query: String): Flow<PagingData<Department>>
}
