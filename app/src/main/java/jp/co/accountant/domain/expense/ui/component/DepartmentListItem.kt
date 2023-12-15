package jp.co.accountant.domain.expense.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import jp.co.accountant.R
import jp.co.accountant.app.data.Department

@Composable
fun DepartmentListItem(
    showLeadingIcon: Boolean = false,
    showTrailingIcon: Boolean = false,
    department: Department,
    onSelected: (Department) -> Unit = {}
) {
    val focusManager = LocalFocusManager.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.list_height))
            .selectable(
                selected = true,
                onClick = {
                    focusManager.clearFocus()
                    onSelected(department)
                }
            )
    ) {
        if (showLeadingIcon) {
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.medium_space)))
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = null,
                modifier = Modifier.size(dimensionResource(id = R.dimen.list_icon_size))
            )
        }
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.medium_space)))
        Text(
            text = department.name,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        if (showTrailingIcon) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_outward),
                contentDescription = null,
                modifier = Modifier.size(dimensionResource(id = R.dimen.list_icon_size))
            )
        } else {
            Text(
                text = department.code,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.medium_space)))
    }
}

@Preview
@Composable
private fun PreviewDepartmentListItem() {
    Surface {
        val item = Department(id = 1, name = "department_name", code = "AB0000")
        Column {
            DepartmentListItem(department = item)
            DepartmentListItem(showLeadingIcon = true, department = item)
            DepartmentListItem(showLeadingIcon = true, showTrailingIcon = true, department = item)
        }
    }
}
