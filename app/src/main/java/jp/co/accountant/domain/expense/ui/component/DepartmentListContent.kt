package jp.co.accountant.domain.expense.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import jp.co.accountant.R
import jp.co.accountant.app.data.department.Department
import jp.co.accountant.app.data.department.DepartmentWithHistory

@Composable
fun DepartmentListContent(
    items: List<DepartmentWithHistory>,
    onSelectDepartment: (Department) -> Unit = {},
) {
    val departmentsWithHistory by rememberUpdatedState(newValue = items)

    LazyColumn(
        modifier = Modifier.padding(top = dimensionResource(R.dimen.large_space))
    ) {
        items(departmentsWithHistory) { departmentWithHistory ->
            DepartmentListItem(
                item = departmentWithHistory,
                onSelected = onSelectDepartment
            )
        }
    }
}