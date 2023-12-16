package jp.co.accountant.domain.expense.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.query
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.accountant.app.data.Department
import jp.co.accountant.domain.expense.data.DepartmentDataSource
import jp.co.accountant.domain.expense.data.DepartmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val departmentRepository: DepartmentRepository,
    private val dataSource: DepartmentDataSource
) : ViewModel() {

    private val _departments = MutableStateFlow(listOf<Department>())
    val departments: StateFlow<List<Department>> = _departments.asStateFlow()

    private var searchResults = listOf<Department>()

    private var searchQuery: String = ""
    private var selectedIndex: Int = 0

    fun onSearch(query: String) = viewModelScope.launch {
        searchQuery = query
        searchDepartments()
    }

    fun onSegmentChange(index: Int) = viewModelScope.launch {
        selectedIndex = index
        searchDepartments()
    }

    private fun searchDepartments() = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            when (selectedIndex) {
                0 -> {
                    val searchResults = dataSource.findDepartmentsByQuery(searchQuery, 50)
                    _departments.value = searchResults.toList()
                }
                1 -> {
                    val searchResults = dataSource.findDepartmentsByQueryWithName(searchQuery, 50)
                    _departments.value = searchResults.toList()
                }
                2 -> {
                    val searchResults = dataSource.findDepartmentsByQueryWithCode(searchQuery, 50)
                    _departments.value = searchResults.toList()
                }
            }
        }
    }
}
