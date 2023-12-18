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

    private var searchQuery: String = ""
    private var selectedSegmentType: SegmentType = SegmentType.NONE

    /**
     * 部門検索を行う
     *
     * @param query 検索クエリ
     */
    fun onSearch(query: String) = viewModelScope.launch {
        searchQuery = query
        searchDepartments()
    }

    /**
     * セグメントの変更を行う
     *
     * @param segmentType 選択されたsegmentType
     */
    fun onSegmentChange(segmentType: SegmentType) = viewModelScope.launch {
        selectedSegmentType = segmentType
        searchDepartments()
    }

    /**
     * 部門の利用履歴を保存する
     *
     * @param departmentId 部門id
     */
    fun onSaveDepartmentHistory(departmentId: Int) = viewModelScope.launch {
        insertHistoryUseCase.insert(departmentId)
    }

    /**
     * 部門検索を行う
     */
    private fun searchDepartments() = viewModelScope.launch {
        val results = findDepartmentsUseCase.findDepartments(
            query = searchQuery,
            segmentType = selectedSegmentType
        )
        _searchResults.value = results
    }
}
