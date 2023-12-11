package jp.co.accountant.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.co.accountant.app.data.AppDatabase
import jp.co.accountant.app.data.DepartmentDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDepartmentDao(@ApplicationContext context: Context): DepartmentDao {
        val appDatabase = AppDatabase.getInstance(context)
        return appDatabase.departmentDao()
    }
}
