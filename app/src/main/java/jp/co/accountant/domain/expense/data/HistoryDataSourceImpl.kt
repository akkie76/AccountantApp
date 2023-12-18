package jp.co.accountant.domain.expense.data

import jp.co.accountant.app.data.history.History
import jp.co.accountant.app.data.history.HistoryDao
import javax.inject.Inject

class HistoryDataSourceImpl @Inject constructor(
    private val historyDao: HistoryDao
) : HistoryDataSource {

    /**
     * Historyを保存する
     *
     * @param departmentId 部門id
     */
    override suspend fun insert(departmentId: Int) {
        val history = History(id = AUTO_INCREMENT_VALUE, departmentId = departmentId)
        historyDao.insertHistory(history)
    }

    companion object {
        private const val AUTO_INCREMENT_VALUE = 0
    }
}
