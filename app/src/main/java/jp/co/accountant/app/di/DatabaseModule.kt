package jp.co.accountant.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.co.accountant.app.data.AppDatabase
import jp.co.accountant.app.data.DepartmentDao
import jp.co.accountant.app.data.HistoryDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDepartmentDao(@ApplicationContext context: Context): DepartmentDao {
        val appDatabase = AppDatabase.getInstance(context)
        return appDatabase.departmentDao()
    }

    @Provides
    fun provideHistoryDao(@ApplicationContext context: Context): HistoryDao {
        val appDatabase = AppDatabase.getInstance(context)
        return appDatabase.historyDao()
    }
}
