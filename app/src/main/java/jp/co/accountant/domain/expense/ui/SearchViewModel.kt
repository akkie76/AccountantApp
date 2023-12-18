package jp.co.accountant.domain.expense.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.accountant.app.data.department.DepartmentWithHistory
import jp.co.accountant.domain.expense.SegmentType
import jp.co.accountant.domain.expense.usecase.FindDepartmentsUseCase
import jp.co.accountant.domain.expense.usecase.InsertHistoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val findDepartmentsUseCase: FindDepartmentsUseCase,
    private val insertHistoryUseCase: InsertHistoryUseCase
) : ViewModel() {

    private val _searchResults = MutableStateFlow(listOf<DepartmentWithHistory>())
    val searchResults: StateFlow<List<DepartmentWithHistory>> = _searchResults.asStateFlow()

    /**
     * 部門検索を行う
     *
     * @param query 検索クエリ
     * @param segmentType 選択されたsegmentType
     */
    fun onSearch(query: String, segmentType: SegmentType) = viewModelScope.launch {
        val results = findDepartmentsUseCase.findDepartments(
            query = query,
            segmentType = segmentType
        )
        _searchResults.value = results
    }

    /**
     * 部門の利用履歴を保存する
     *
     * @param departmentId 部門id
     */
    fun onSaveDepartmentHistory(departmentId: Int) = viewModelScope.launch {
        insertHistoryUseCase.insert(departmentId)
    }
}
