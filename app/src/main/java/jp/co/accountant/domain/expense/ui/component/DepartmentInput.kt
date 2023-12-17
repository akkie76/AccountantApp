package jp.co.accountant.domain.expense.ui.component

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import jp.co.accountant.R
import jp.co.accountant.app.ui.PreviewSurface

@Composable
fun DepartmentInput(
    text: String = "",
    onValueChange: (String) -> Unit = {}
) {
    var query by remember { mutableStateOf(text) }
    var code by remember { mutableStateOf("") }
    var showSearchDialog by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.large_space))) {
        Text(
            text = stringResource(R.string.department_title),
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.medium_space)),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = query,
                placeholder = {
                    Text(text = stringResource(R.string.department_placeholder))
                },
                onValueChange = { newValue ->
                    query = newValue
                },
                modifier = Modifier
                    .weight(1f)
                    .height(IntrinsicSize.Min),
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = {
                            query = ""
                            code = ""
                        }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = null
                            )
                        }
                    }
                },
                singleLine = true
            )
            Button(
                onClick = {
                    showSearchDialog = true
                    focusManager.clearFocus()
                },
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .padding(start = dimensionResource(R.dimen.medium_space)),
                shape = RoundedCornerShape(dimensionResource(R.dimen.search_button_shape))
            ) {
                Text(
                    text = stringResource(R.string.search_button_title),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
        Text(
            text = code,
            modifier = Modifier
                .padding(start = dimensionResource(R.dimen.large_space))
                .padding(top = dimensionResource(R.dimen.small_space)),
            style = MaterialTheme.typography.bodySmall
        )
    }

    if (showSearchDialog) {
        SearchDialog(
            text = query,
            onSelectDepartment = { department ->
                query = department.name
                code = "部門コード: ${department.code}"
                onValueChange(query)
                showSearchDialog = false
            },
            onDismissRequest = {
                showSearchDialog = false
            }
        )
    }
}

@Preview
@Composable
private fun PreviewDepartmentInput() {
    PreviewSurface {
        DepartmentInput()
    }
}
