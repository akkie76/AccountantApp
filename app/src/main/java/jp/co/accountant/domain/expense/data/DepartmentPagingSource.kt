package jp.co.accountant.domain.expense.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import jp.co.accountant.app.data.Department
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DepartmentPagingSource(
    private val departmentDataSource: DepartmentDataSource
) : PagingSource<Int, Department>() {

    private var totalCount: Int = 0
    private var lastDepartmentId: Int = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Department> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val departments = withContext(Dispatchers.IO) {
                departmentDataSource.findDepartmentsAfterId(lastDepartmentId, params.loadSize)
            }

            totalCount += departments.size
            lastDepartmentId = departments.last().id

            LoadResult.Page(
                data = departments,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == (totalCount % params.loadSize)) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Department>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}
