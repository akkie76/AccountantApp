package jp.co.accountant.domain.expense.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import jp.co.accountant.app.data.Department
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DepartmentRepositoryImpl @Inject constructor(
    private val departmentDataSource: DepartmentDataSource
) : DepartmentRepository {

    override fun fetchDepartments(): Flow<PagingData<Department>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = MAX_PAGING_SIZE),
            pagingSourceFactory = { DepartmentPagingSource(departmentDataSource) }
        ).flow
    }

    companion object {
        private const val MAX_PAGING_SIZE = 10
    }
}
