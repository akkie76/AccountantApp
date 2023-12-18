package jp.co.accountant.app.data.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface HistoryDao {

    /**
     * Departmentの利用履歴を保存する
     *
     * @param history History
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: History)
}
