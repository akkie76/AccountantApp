package jp.co.accountant.domain.expense.usecase

interface InsertHistoryUseCase {

    /**
     * Historyを保存する
     *
     * @param departmentId 部門id
     */
    suspend fun insert(departmentId: Int)
}
