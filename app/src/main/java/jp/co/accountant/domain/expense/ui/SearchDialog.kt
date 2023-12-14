package jp.co.accountant.domain.expense.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import jp.co.accountant.app.data.Department
import jp.co.accountant.app.ui.SearchTextField
import jp.co.accountant.app.ui.theme.AccountantAppTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun SearchDialog(
//    text: String,
//    departments: LazyPagingItems<Department>,
//    onSearchQuery: (String) -> Unit = {},
    viewModel: ExpenseViewModel = hiltViewModel(),
    onClickCancel: () -> Unit = {}
) {
    var query by remember { mutableStateOf(viewModel.searchQuery) }
    val departments = viewModel.departments.collectAsLazyPagingItems()

    AlertDialog(
        modifier = Modifier.requiredHeight(600.dp),
        onDismissRequest = {
        },
        title = {
            SegmentedButtonRow()
        },
        text = {
            Column(Modifier.fillMaxHeight()) {
                // TODO: バックキーでフォーカスを外す
                SearchTextField(
                    text = query,
                    onValueChange = { newValue ->
                        query = newValue
                        viewModel.onSearch(newValue)
                    }, onClickLeadingIcon = {
                        viewModel.onSearch("Mike")
                    }
                )
                LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                    items(
                        count = departments.itemCount,
                        key = departments.itemKey()
                    ) { index ->
                        val department = departments[index] ?: return@items
                        Text(text = "${department.id}, ${department.name}")
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
//@Preview
//@Composable
//private fun PreviewSearchDialog() {
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
//}