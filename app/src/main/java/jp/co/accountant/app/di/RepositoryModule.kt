package jp.co.accountant.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.co.accountant.domain.expense.data.DepartmentDataSource
import jp.co.accountant.domain.expense.data.DepartmentDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindDepartmentDataSource(
        departmentDataSourceImpl: DepartmentDataSourceImpl
    ): DepartmentDataSource
}
