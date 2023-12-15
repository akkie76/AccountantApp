package jp.co.accountant.domain.expense.ui

import androidx.compose.foundation.MutatePriority
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.accountant.app.data.Department
import jp.co.accountant.domain.expense.data.DepartmentDataSource
import jp.co.accountant.domain.expense.data.DepartmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val departmentRepository: DepartmentRepository,
    private val dataSource: DepartmentDataSource
) : ViewModel() {

    var searchQuery: String = "John"
        private set

//    var departments = departmentRepository.fetchDepartments(searchQuery).cachedIn(viewModelScope)
//        private set

    private val _departments = MutableStateFlow(listOf<Department>())
    val departments: StateFlow<List<Department>> = _departments.asStateFlow()

    //flowOf(PagingData.empty<Department>())

    fun onSearch(query: String) = viewModelScope.launch {
        searchQuery = query
        //departments = departmentRepository.fetchDepartments(searchQuery).cachedIn(viewModelScope)
        withContext(Dispatchers.Default) {
            val newList = dataSource.findDepartmentsByQuery(searchQuery,0, 50)
            _departments.value = newList
        }
    }
}
