package jp.co.accountant.domain.setting.data

interface SettingDataSource {

    /**
     * アプリ起動時の設定としてDatabaseに接続するため、設定を保存する
     */
    suspend fun configure()
}
