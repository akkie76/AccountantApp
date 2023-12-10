package jp.co.accountant.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.co.accountant.domain.expense.data.DepartmentRepository
import jp.co.accountant.domain.expense.data.DepartmentRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindDepartmentRepository(
        departmentRepositoryImpl: DepartmentRepositoryImpl
    ): DepartmentRepository
}
