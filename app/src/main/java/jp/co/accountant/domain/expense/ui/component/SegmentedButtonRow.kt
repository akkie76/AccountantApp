package jp.co.accountant.domain.expense.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SegmentedButtonRow(
    onCheckedChange: (Int) -> Unit = {}
) {
    var selectedIndex by remember { mutableStateOf(0) }
    val options = listOf("全て", "部門", "コード")

    MultiChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                icon = {},
                onCheckedChange = {
                    selectedIndex = index
                    onCheckedChange(selectedIndex)
                },
                checked = selectedIndex == index
            ) {
                Text(label)
            }
        }
    }
}
