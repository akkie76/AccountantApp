package jp.co.accountant.domain.expense.usecase

import jp.co.accountant.app.data.department.DepartmentWithHistory
import jp.co.accountant.domain.expense.SegmentType
import jp.co.accountant.domain.expense.data.DepartmentDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FindDepartmentsUseCaseImpl @Inject constructor(
    private val departmentDataSource: DepartmentDataSource
) : FindDepartmentsUseCase {

    /**
     * 条件に一致する部門をDatabaseから検索する
     *
     * @param query 検索クエリ
     * @param segmentType 選択されたsegmentType
     * @return 利用履歴を含めた部門情報配列
     */
    override suspend fun findDepartments(query: String, segmentType: SegmentType): List<DepartmentWithHistory> {
        return withContext(Dispatchers.Default) {
            val limit = decideQueryLimit(query)
            when (segmentType) {
                SegmentType.NONE -> {
                    departmentDataSource.findDepartmentsByQuery(query, limit)
                }
                SegmentType.NAME -> {
                    departmentDataSource.findDepartmentsByQueryWithName(query, limit)
                }
                SegmentType.CODE -> {
                    departmentDataSource.findDepartmentsByQueryWithCode(query, limit)
                }
            }
        }
    }

    /**
     * 検索クエリのLimitをquery数から段階的に判断し返却する
     *
     * @param query 検索クエリ
     * @return Limit数
     */
    private fun decideQueryLimit(query: String): Int {
        return when {
            query.isEmpty() -> MAX_QUERY_RESULT_LIMIT
            query.length < 4 -> NORMAL_QUERY_RESULT_LIMIT
            else -> MINI_QUERY_RESULT_LIMIT
        }
    }

    companion object {
        private const val MAX_QUERY_RESULT_LIMIT = 100
        private const val NORMAL_QUERY_RESULT_LIMIT = 50
        private const val MINI_QUERY_RESULT_LIMIT = 25
    }
}
