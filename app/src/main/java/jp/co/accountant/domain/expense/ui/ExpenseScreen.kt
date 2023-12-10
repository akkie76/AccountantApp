package jp.co.accountant.domain.expense.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import jp.co.accountant.app.ui.SearchTextField
import jp.co.accountant.app.ui.theme.AccountantAppTheme
import kotlinx.coroutines.launch

@Composable
fun ExpenseScreen(
    viewModel: ExpenseViewModel = hiltViewModel()
) {
    ExpenseContent(viewModel.query) { query ->
        viewModel.onSearch(query)
    }
}

@Composable
private fun ExpenseContent(
    query: String = "",
    onSearchQuery: suspend (String) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            var text by remember { mutableStateOf(query) }

            SearchTextField(
                text = text,
                onValueChange = { newValue ->
                    text = newValue
                },
                onClickLeadingIcon = {
                    coroutineScope.launch {
                        onSearchQuery(text)
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewExpenseContent() {
    AccountantAppTheme {
        ExpenseContent()
    }
}
