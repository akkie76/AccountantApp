package jp.co.accountant.domain.expense.data

interface HistoryDataSource {

    /**
     * Historyを保存する
     *
     * @param departmentId 部門id
     */
    suspend fun insert(departmentId: Int)
}
