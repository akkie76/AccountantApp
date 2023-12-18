package jp.co.accountant.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.co.accountant.app.data.AppDatabase
import jp.co.accountant.app.data.department.DepartmentDao
import jp.co.accountant.app.data.history.HistoryDao
import jp.co.accountant.app.data.setting.SettingDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDepartmentDao(@ApplicationContext context: Context): DepartmentDao {
        return createDatabase(context).departmentDao()
    }

    @Provides
    fun provideHistoryDao(@ApplicationContext context: Context): HistoryDao {
        return createDatabase(context).historyDao()
    }

    @Provides
    fun provideSettingDao(@ApplicationContext context: Context): SettingDao {
        return createDatabase(context).settingDao()
    }

    /**
     * AppDatabaseを生成する
     *
     * @param context Context
     */
    private fun createDatabase(context: Context) = AppDatabase.getInstance(context)
}
