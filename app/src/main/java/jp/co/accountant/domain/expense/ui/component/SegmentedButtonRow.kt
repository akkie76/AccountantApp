package jp.co.accountant.domain.expense.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.co.accountant.domain.expense.SegmentType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SegmentedButtonRow(
    segmentType: SegmentType,
    onCheckedChange: (SegmentType) -> Unit = {}
) {
    val options = listOf("全て", "部門", "コード")

    MultiChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                icon = {},
                onCheckedChange = {
                    onCheckedChange(SegmentType.from(index))
                },
                checked = segmentType.value == index
            ) {
                Text(label)
            }
        }
    }
}
