package jp.co.accountant.domain.search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
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
import jp.co.accountant.app.ui.SearchTextField

@Composable
fun SearchScreen() {
    var text by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(true) }

    Scaffold { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            Button(onClick = { showDialog = true }) {
                Text("Show")
            }

            if (showDialog) {
                AlertDialog(
                    modifier = Modifier.requiredHeight(600.dp),
                    onDismissRequest = {},
                    title = {
                        SegmentedButtonRow()
                    },
                    text = {
                        Column(Modifier.fillMaxHeight()) {
                            // TODO: バックキーでフォーカスを外す
                            SearchTextField(text = text, onValueChange = { newValue ->
                                text = newValue
                            }, onClickLeadingIcon = {})
                            LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                                items(1000) { index ->
                                    Text(text = "Item: $index")
                                }
                            }
                        }
                    },
                    confirmButton = {
                        Text(
                            text = "キャンセル",
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .clickable { showDialog = false },
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SegmentedButtonRow() {
    var selectedIndex by remember { mutableStateOf(0) }
    val options = listOf("全て", "コード", "部門")

    MultiChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                icon = {},
                onCheckedChange = { selectedIndex = index },
                checked = selectedIndex == index
            ) {
                Text(label)
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSearchScreen() {
    SearchScreen()
}
