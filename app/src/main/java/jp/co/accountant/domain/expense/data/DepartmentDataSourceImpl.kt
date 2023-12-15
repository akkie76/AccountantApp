package jp.co.accountant.domain.expense.data

import jp.co.accountant.app.data.Department
import jp.co.accountant.app.data.DepartmentDao
import javax.inject.Inject

class DepartmentDataSourceImpl @Inject constructor(
    private val departmentDao: DepartmentDao
) : DepartmentDataSource {

    override suspend fun findDepartmentsByQuery(
        query: String,
        limit: Int
    ): List<Department> {
        return departmentDao.findDepartmentsByQuery(
            query = query,
            startAfterId = START_AFTER_ID,
            limit = limit
        )
    }

    override suspend fun findDepartmentsByQueryWithColumn(
        query: String,
        column: String,
        limit: Int
    ): List<Department> {
        return departmentDao.findDepartmentsByQueryWithColumn(
            query = query,
            column = column,
            startAfterId = START_AFTER_ID,
            limit = limit
        )
    }

    companion object {
        private const val START_AFTER_ID = 0
    }
}
