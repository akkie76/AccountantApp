package jp.co.accountant.domain.expense.usecase

import jp.co.accountant.domain.expense.data.HistoryDataSource
import javax.inject.Inject

class InsertHistoryUseCaseImpl @Inject constructor(
    private val historyDataSource: HistoryDataSource
) : InsertHistoryUseCase {

    /**
     * Historyを保存する
     *
     * @param departmentId 部門id
     */
    override suspend fun insert(departmentId: Int) {
        historyDataSource.insert(departmentId)
    }
}
