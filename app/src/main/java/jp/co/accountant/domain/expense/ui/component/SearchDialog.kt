package jp.co.accountant.domain.expense.ui.component

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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import jp.co.accountant.R
import jp.co.accountant.app.data.Department
import jp.co.accountant.app.ui.SearchTextField
import jp.co.accountant.domain.expense.SegmentType
import jp.co.accountant.domain.expense.ui.SearchViewModel
import kotlinx.coroutines.launch

@Composable
fun SearchDialog(
    viewModel: SearchViewModel = hiltViewModel(),
    text: String,
    onSelectDepartment: (Department) -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
    var query by remember { mutableStateOf(text) }
    var placeHolderResId by remember { mutableStateOf(R.string.placeholder_search) }
    val searchResults by viewModel.searchResults.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(text) {
        viewModel.onSearch(text)
    }

    AlertDialog(
        modifier = Modifier.requiredHeight(dimensionResource(R.dimen.dialog_height)),
        onDismissRequest = {
        },
        title = {
            SegmentedButtonRow { segmentType ->
                placeHolderResId = when (segmentType) {
                    SegmentType.NONE -> R.string.placeholder_search
                    SegmentType.NAME -> R.string.placeholder_search_name
                    SegmentType.CODE -> R.string.placeholder_search_code
                }
                viewModel.onSegmentChange(segmentType)
            }
        },
        text = {
            Column(Modifier.fillMaxHeight()) {
                SearchTextField(
                    text = query,
                    placeholder = {
                        Text(
                            text = stringResource(placeHolderResId),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
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
                LazyColumn(
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.large_space))
                ) {
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
                text = stringResource(R.string.cancel),
                modifier = Modifier
                    .padding(horizontal = dimensionResource(R.dimen.medium_space))
                    .clickable { onDismissRequest() },
                style = MaterialTheme.typography.labelLarge
            )
        }
    )
}
