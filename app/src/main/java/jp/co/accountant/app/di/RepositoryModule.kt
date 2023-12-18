package jp.co.accountant.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.co.accountant.domain.expense.data.DepartmentDataSource
import jp.co.accountant.domain.expense.data.DepartmentDataSourceImpl
import jp.co.accountant.domain.expense.data.HistoryDataSource
import jp.co.accountant.domain.expense.data.HistoryDataSourceImpl
import jp.co.accountant.domain.setting.data.SettingDataSource
import jp.co.accountant.domain.setting.data.SettingDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindDepartmentDataSource(
        departmentDataSourceImpl: DepartmentDataSourceImpl
    ): DepartmentDataSource

    @Binds
    abstract fun bindHistoryDataSource(
        historyDataSourceImpl: HistoryDataSourceImpl
    ): HistoryDataSource

    @Binds
    abstract fun bindSettingDataSource(
        settingDataSourceImpl: SettingDataSourceImpl
    ): SettingDataSource
}
