package jp.co.accountant.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.co.accountant.domain.expense.usecase.FindDepartmentsUseCase
import jp.co.accountant.domain.expense.usecase.FindDepartmentsUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun findFindDepartmentsUseCase(
        findDepartmentsUseCaseImpl: FindDepartmentsUseCaseImpl
    ): FindDepartmentsUseCase
}
