package jp.co.accountant.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.co.accountant.domain.expense.usecase.FindDepartmentsUseCase
import jp.co.accountant.domain.expense.usecase.FindDepartmentsUseCaseImpl
import jp.co.accountant.domain.expense.usecase.InsertHistoryUseCase
import jp.co.accountant.domain.expense.usecase.InsertHistoryUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindFindFindDepartmentsUseCase(
        findDepartmentsUseCaseImpl: FindDepartmentsUseCaseImpl
    ): FindDepartmentsUseCase

    @Binds
    abstract fun bindInsertHistoryUseCase(
        insertHistoryUseCaseImpl: InsertHistoryUseCaseImpl
    ): InsertHistoryUseCase
}
