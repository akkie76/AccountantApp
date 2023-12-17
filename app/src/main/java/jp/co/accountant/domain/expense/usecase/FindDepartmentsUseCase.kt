package jp.co.accountant.domain.expense.usecase

import jp.co.accountant.app.data.DepartmentWithHistory
import jp.co.accountant.domain.expense.SegmentType

interface FindDepartmentsUseCase {

    /**
     * 条件に一致する部門をDataBaseから検索する
     *
     * @param query 検索クエリ
     * @param segmentType 選択されたsegmentType
     * @return 利用履歴を含めた部門情報配列
     */
    suspend fun findDepartments(query: String, segmentType: SegmentType): List<DepartmentWithHistory>
}
