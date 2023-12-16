package jp.co.accountant.domain.expense.usecase

import jp.co.accountant.app.data.Department

interface FindDepartmentsUseCase {

    /**
     * 条件に一致する部門をDataBaseから検索する
     *
     * @param query 検索クエリ
     * @param index 選択されたindex
     * @return 条件に一致した部門配列
     */
    suspend fun findDepartments(query: String, index: Int): List<Department>
}
