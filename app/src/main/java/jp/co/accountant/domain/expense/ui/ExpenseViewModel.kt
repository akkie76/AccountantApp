package jp.co.accountant.domain.expense.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.accountant.domain.expense.data.DepartmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val departmentRepository: DepartmentRepository
) : ViewModel() {

    var query: String = ""
        private set

    suspend fun onSearch(query: String) {
        this.query = query
        val data = withContext(Dispatchers.IO) { departmentRepository.fetchData() }
    }
}
