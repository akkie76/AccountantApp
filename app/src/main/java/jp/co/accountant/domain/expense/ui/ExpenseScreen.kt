package jp.co.accountant.domain.expense.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import jp.co.accountant.app.data.Department

@Composable
fun ExpenseScreen(
    viewModel: ExpenseViewModel = hiltViewModel()
) {
    val departments = viewModel.departments.collectAsLazyPagingItems()

    ExpenseContent(departments, viewModel.query) { query ->
        viewModel.onSearch(query)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExpenseContent(
    pagingItems: LazyPagingItems<Department>,
    query: String = "",
    onSearchQuery: suspend (String) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    var text by remember { mutableStateOf(query) }
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "経費申請") })
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = text,
                        onValueChange = {},
                        modifier = Modifier
                            .weight(1f)
                            .height(IntrinsicSize.Min),
                        trailingIcon = {
                            IconButton(onClick = {
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .height(IntrinsicSize.Min)
                            .padding(start = 8.dp),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "検索",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}

// @Preview
// @Composable
// private fun PreviewExpenseContent() {
//    AccountantAppTheme {
//        ExpenseContent()
//    }
// }
