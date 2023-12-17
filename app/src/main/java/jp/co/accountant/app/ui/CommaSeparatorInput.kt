package jp.co.accountant.app.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.core.text.isDigitsOnly
import jp.co.accountant.R

@Composable
fun CommaSeparatorInput(
    modifier: Modifier = Modifier,
    text: String = "",
    @StringRes titleId: Int,
    @StringRes supportingTextId: Int,
    onValueChange: (String) -> Unit = {}
) {
    val visualTransformation = CommaSeparatorTransformation()

    var input by remember { mutableStateOf(text) }

    val transformedText = remember(input, visualTransformation) {
        visualTransformation.filter(AnnotatedString(input))
    }.text.text

    Column(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.large_space))) {
        Text(
            text = stringResource(titleId),
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.medium_space)),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = input,
            onValueChange = { value ->
                if (value.isDigitsOnly()) {
                    input = value
                    onValueChange(transformedText)
                }
            },
            modifier = modifier.fillMaxWidth(),
            // FIXME: フォントサイズの高さを修正する
            //textStyle = TextStyle.Default.copy(textAlign = TextAlign.Right),
            suffix = {
                Text(text = stringResource(R.string.suffix_yen))
            },
            supportingText = {
                Text(text = stringResource(supportingTextId))
            },
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
    }
}

private class CommaSeparatorTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val output = buildString {
            val reversed = text.text.reversed()
            for (index in reversed.indices) {
                if (index > 0 && index % 3 == 0) {
                    append(',')
                }
                append(reversed[index])
            }
        }.reversed()
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val totalSeparatorCount = (text.length - 1) / 3
                val rightSeparatorCount = (text.length - 1 - offset) / 3
                val leftSeparatorCount = totalSeparatorCount - rightSeparatorCount
                return offset + leftSeparatorCount
            }

            override fun transformedToOriginal(offset: Int): Int {
                val totalSeparatorCount = (text.length - 1) / 3
                val rightSeparatorCount = (output.length - offset) / 4
                val leftSeparatorCount = totalSeparatorCount - rightSeparatorCount
                return offset - leftSeparatorCount
            }
        }
        return TransformedText(text = AnnotatedString(output), offsetMapping = offsetMapping)
    }
}
