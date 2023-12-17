package jp.co.accountant.domain.expense.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import jp.co.accountant.R
import jp.co.accountant.app.data.Department
import jp.co.accountant.app.data.DepartmentWithHistory
import jp.co.accountant.app.ui.BaseListItem

@Composable
fun DepartmentListItem(
    item: DepartmentWithHistory,
    onSelected: (Department) -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    val department = item.department
    val hasHistory = item.hasHistory

    val trailingIcon: @Composable (() -> Unit) = {
        Icon(
            painter = painterResource(R.drawable.arrow_outward),
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(R.dimen.list_icon_size))
        )
    }

    Card(
        modifier = Modifier.padding(bottom = dimensionResource(R.dimen.small_space)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        BaseListItem(
            modifier = Modifier.selectable(
                selected = true,
                onClick = {
                    focusManager.clearFocus()
                    onSelected(department)
                }
            ),
            headLine = department.name,
            supportingText = department.code,
            trailingIcon = if (hasHistory) trailingIcon else null
        )
    }
}

@Preview
@Composable
private fun PreviewDepartmentListItem() {
    Surface {
        val department = Department(id = 1, name = "department_name", code = "AB0000")
        val item1 = DepartmentWithHistory(department, true)
        val item2 = DepartmentWithHistory(department, false)
        Column {
            DepartmentListItem(item1)
            DepartmentListItem(item2)
        }
    }
}
