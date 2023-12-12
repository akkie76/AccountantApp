package jp.co.accountant.domain.expense.data

import jp.co.accountant.app.data.Department
import jp.co.accountant.app.data.DepartmentDao
import javax.inject.Inject

class DepartmentDataSourceImpl @Inject constructor(
    private val departmentDao: DepartmentDao
) : DepartmentDataSource {
    override suspend fun findDepartmentsAfterId(startAfterId: Int, limit: Int): List<Department> {
        return departmentDao.findDepartmentsAfterId(startAfterId = startAfterId, limit = limit)
    }
}
