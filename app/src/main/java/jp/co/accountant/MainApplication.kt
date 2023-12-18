package jp.co.accountant

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import jp.co.accountant.domain.setting.data.SettingDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application(), CoroutineScope by MainScope() {

    @Inject lateinit var settingDataSource: SettingDataSource

    override fun onCreate() {
        super.onCreate()
        configureDatabase()
    }

    /**
     * CoroutineWorkerを実行させるため、settingsへアクセスする
     */
    private fun configureDatabase() {
        launch {
            settingDataSource.configure()
        }
    }
}
