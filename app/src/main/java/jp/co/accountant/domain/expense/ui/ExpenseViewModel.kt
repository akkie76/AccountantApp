package jp.co.accountant.domain.expense.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.accountant.app.data.Department
import jp.co.accountant.domain.expense.data.DepartmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val departmentRepository: DepartmentRepository
) : ViewModel() {

    var searchQuery: String = "John"
        private set

    var departments = departmentRepository.fetchDepartments(searchQuery).cachedIn(viewModelScope)
        private set

    //flowOf(PagingData.empty<Department>())

    fun onSearch(query: String) = viewModelScope.launch {
        searchQuery = query
        departments = departmentRepository.fetchDepartments(searchQuery).cachedIn(viewModelScope)
    }
}
