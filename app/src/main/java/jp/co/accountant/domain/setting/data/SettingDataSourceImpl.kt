package jp.co.accountant.domain.setting.data

import jp.co.accountant.app.data.setting.Setting
import jp.co.accountant.app.data.setting.SettingDao
import javax.inject.Inject

class SettingDataSourceImpl @Inject constructor(
    private val settingDao: SettingDao
) : SettingDataSource {

    /**
     * アプリ起動時の設定としてDatabaseに接続するため、設定を保存する
     */
    override suspend fun configure() {
        settingDao.insertSetting(Setting.INITIAL)
    }
}
