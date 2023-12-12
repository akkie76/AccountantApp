package jp.co.accountant.domain.expense.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import jp.co.accountant.app.data.Department
import jp.co.accountant.app.ui.SearchTextField
import kotlinx.coroutines.launch

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

    Scaffold { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            LazyColumn {
                items(
                    count = pagingItems.itemCount,
                    key = pagingItems.itemKey()
                ) { index ->
                    val item = pagingItems[index] ?: return@items
                    Text(text = "id: ${item.id}, name: ${item.name}")
                }
            }

//            Column {
//                SearchTextField(
//                    text = text,
//                    onValueChange = { newValue ->
//                        text = newValue
//                    },
//                    onClickLeadingIcon = {
//                        coroutineScope.launch {
//                            //onSearchQuery(text)
//                            showBottomSheet = true
//                        }
//                    }
//                )
//
//                val checkedList = remember { mutableStateListOf<Int>() }
//                val options = listOf("Favorites", "Trending", "Saved")
//                val icons = listOf(
//                    Icons.Filled.Star,
//                    Icons.Filled.Star,
//                    Icons.Filled.Star
//                )
//
//                MultiChoiceSegmentedButtonRow {
//                    options.forEachIndexed { index, label ->
//                        SegmentedButton(
//                            shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
//                            icon = {
//                                SegmentedButtonDefaults.Icon(active = index in checkedList) {
//                                    Icon(
//                                        imageVector = icons[index],
//                                        contentDescription = null,
//                                        modifier = Modifier.size(SegmentedButtonDefaults.IconSize)
//                                    )
//                                }
//                            },
//                            onCheckedChange = {
//                                if (index in checkedList) {
//                                    checkedList.remove(index)
//                                } else {
//                                    checkedList.add(index)
//                                }
//                            },
//                            checked = index in checkedList
//                        ) {
//                            Text(label)
//                        }
//                    }
//                }
//            }

            if (showBottomSheet) {
                ModalBottomSheet(
                    modifier = Modifier.fillMaxSize(),
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState
                ) {
                    Column {
                        SearchTextField(
                            text = text,
                            onValueChange = { newValue ->
                                text = newValue
                            },
                            onClickLeadingIcon = {
                                coroutineScope.launch {
                                    // onSearchQuery(text)
                                    showBottomSheet = true
                                }
                            }
                        )

                        val checkedList = remember { mutableStateListOf<Int>() }
                        val options = listOf("Favorites", "Trending", "Saved")
                        val icons = listOf(
                            Icons.Filled.Star,
                            Icons.Filled.Star,
                            Icons.Filled.Star
                        )

                        MultiChoiceSegmentedButtonRow {
                            options.forEachIndexed { index, label ->
                                SegmentedButton(
                                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                                    icon = {
                                        SegmentedButtonDefaults.Icon(active = index in checkedList) {
                                            Icon(
                                                imageVector = icons[index],
                                                contentDescription = null,
                                                modifier = Modifier.size(SegmentedButtonDefaults.IconSize)
                                            )
                                        }
                                    },
                                    onCheckedChange = {
                                        if (index in checkedList) {
                                            checkedList.remove(index)
                                        } else {
                                            checkedList.add(index)
                                        }
                                    },
                                    checked = index in checkedList
                                ) {
                                    Text(label)
                                }
                            }
                        }
                    }

//                    Button(onClick = {
//                        coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
//                            if (!sheetState.isVisible) {
//                                showBottomSheet = false
//                            }
//                        }
//                    }) {
//                        Text("Hide bottom sheet")
//                    }
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
