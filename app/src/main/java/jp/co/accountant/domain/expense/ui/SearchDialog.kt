package jp.co.accountant.domain.expense.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import jp.co.accountant.app.ui.SearchTextField
import jp.co.accountant.domain.expense.ui.component.DepartmentListItem
import kotlinx.coroutines.launch

@Composable
fun SearchDialog(
    viewModel: ExpenseViewModel = hiltViewModel(),
    text: String,
    onClickCancel: () -> Unit = {}
) {
    var query by remember { mutableStateOf(text) }
    val departments by viewModel.departments.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(text) {
        viewModel.onSearch(text)
    }

    AlertDialog(
        modifier = Modifier.requiredHeight(600.dp),
        onDismissRequest = {
        },
        title = {
            SegmentedButtonRow()
        },
        text = {
            Column(Modifier.fillMaxHeight()) {
                SearchTextField(
                    text = query,
                    onValueChange = { newValue ->
                        query = newValue
                        scope.launch {
                            viewModel.onSearch(query)
                        }
                    },
                    onClickLeadingIcon = {
                        scope.launch {
                            viewModel.onSearch(query)
                        }
                    }
                )
                LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                    items(departments) {
                        DepartmentListItem(department = it)
                    }
                }
            }
        },
        confirmButton = {
            Text(
                text = "キャンセル",
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { onClickCancel() },
                style = MaterialTheme.typography.labelLarge
            )
        }
    )
}
//
// @Preview
// @Composable
// private fun PreviewSearchDialog() {
//    AccountantAppTheme {
//        Surface {
//            val dummyDepartments = mutableListOf<Department>()
//            for (i in 1..10) {
//                val dummyDepartment = Department(
//                    id = i,
//                    name = "Department $i"
//                )
//                dummyDepartments.add(dummyDepartment)
//            }
//            val emptyPagingData = flowOf(PagingData.from(dummyDepartments))
//            val emptyLazyPagingItems = emptyPagingData.collectAsLazyPagingItems()
//            SearchDialog(text = "", departments = emptyLazyPagingItems)
//        }
//    }
// }
