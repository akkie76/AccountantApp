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
            limit = limit
        )
    }

    override suspend fun findDepartmentsByQueryWithName(
        query: String,
        limit: Int
    ): List<Department> {
        return departmentDao.findDepartmentsByQueryWithName(
            query = query,
            limit = limit
        )
    }

    override suspend fun findDepartmentsByQueryWithCode(
        query: String,
        limit: Int
    ): List<Department> {
        return departmentDao.findDepartmentsByQueryWithCode(
            query = query,
            limit = limit
        )
    }
}
