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
import jp.co.accountant.app.data.Department
import jp.co.accountant.app.ui.SearchTextField
import jp.co.accountant.domain.expense.ui.component.DepartmentListItem
import kotlinx.coroutines.launch

@Composable
fun SearchDialog(
    viewModel: ExpenseViewModel = hiltViewModel(),
    text: String,
    onSelectDepartment: (Department) -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
    var query by remember { mutableStateOf(text) }
    val searchResults by viewModel.searchResults.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(text) {
        viewModel.onSearch(text)
    }

    AlertDialog(
        modifier = Modifier.requiredHeight(600.dp),
        onDismissRequest = {
        },
        title = {
            SegmentedButtonRow { selectedIndex ->
                viewModel.onSegmentChange(selectedIndex)
            }
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
                    items(searchResults) { departmentWithHistory ->
                        DepartmentListItem(departmentWithHistory) { department ->
                            viewModel.onSaveDepartmentHistory(department.id)
                            onSelectDepartment(department)
                        }
                    }
                }
            }
        },
        confirmButton = {
            Text(
                text = "キャンセル",
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { onDismissRequest() },
                style = MaterialTheme.typography.labelLarge
            )
        }
    )
}
