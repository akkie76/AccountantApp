package jp.co.accountant.domain.expense.usecase

import jp.co.accountant.app.data.DepartmentWithHistory

interface FindDepartmentsUseCase {

    /**
     * 条件に一致する部門をDataBaseから検索する
     *
     * @param query 検索クエリ
     * @param index 選択されたindex
     * @return 利用履歴を含めた部門情報配列
     */
    suspend fun findDepartments(query: String, index: Int): List<DepartmentWithHistory>
}
