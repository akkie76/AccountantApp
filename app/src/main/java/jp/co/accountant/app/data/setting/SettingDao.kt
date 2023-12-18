package jp.co.accountant.app.data.setting

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface SettingDao {

    /**
     * Settingをinsertする
     *
     * @param setting Databaseへのアクセス設定
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSetting(setting: Setting)
}
