package jp.co.accountant.domain.expense.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.accountant.domain.expense.data.DepartmentRepository
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val departmentRepository: DepartmentRepository
) : ViewModel() {

    init {
        departmentRepository.fetchData()
    }

    var query: String = ""
        private set

    suspend fun onSearch(query: String) {
        this.query = query
    }
}
