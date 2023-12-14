package jp.co.accountant.domain.expense.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.accountant.app.data.Department
import jp.co.accountant.domain.expense.data.DepartmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val departmentRepository: DepartmentRepository
) : ViewModel() {

    var searchQuery: String = ""
        private set

    var departments = flowOf(PagingData.empty<Department>())
        private set

    fun onSearch(query: String) = viewModelScope.launch {
        searchQuery = query
        departments = withContext(Dispatchers.IO) {
            departmentRepository.fetchDepartments(searchQuery).cachedIn(viewModelScope)
        }
    }
}
