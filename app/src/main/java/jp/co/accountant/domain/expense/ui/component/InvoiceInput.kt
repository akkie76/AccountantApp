package jp.co.accountant.domain.expense.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.isDigitsOnly
import jp.co.accountant.R
import jp.co.accountant.app.ui.DescriptionText
import jp.co.accountant.app.ui.InputTitle
import jp.co.accountant.app.ui.PreviewSurface

@Composable
fun InvoiceInput(
    text: String = "",
    onValueChange: (String) -> Unit = {}
) {
    var input by remember { mutableStateOf(text) }

    val visualTransformation = InvoiceBusinessNumberVisualTransformation()

    val transformedText = remember(input, visualTransformation) {
        visualTransformation.filter(AnnotatedString(input))
    }.text.text

    Column(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.large_space))) {
        InputTitle(text = stringResource(R.string.invoice_title))
        OutlinedTextField(
            value = input,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { newValue ->
                if (newValue.isDigitsOnly() && newValue.length <= MAX_LENGTH) {
                    input = newValue
                    onValueChange(transformedText)
                }
            },
            trailingIcon = {
                if (input.isNotEmpty()) {
                    IconButton(onClick = {
                        input = ""
                    }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null
                        )
                    }
                }
            },
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        DescriptionText(text = stringResource(R.string.invoice_description))
    }
}

private const val MAX_LENGTH = 14
private const val INVOICE_BUSINESS_NUMBER_PREFIX = 'T'

private class InvoiceBusinessNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            text = AnnotatedString(
                buildString {
                    text.forEachIndexed { i, char ->
                        if (i == 0) {
                            append(INVOICE_BUSINESS_NUMBER_PREFIX)
                        }
                        append(char)
                    }
                }
            ),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return if (offset < 1) offset else offset + 1
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return if (offset < 1) offset else offset - 1
                }
            }
        )
    }
}

@Preview
@Composable
private fun PreviewInvoiceInput() {
    PreviewSurface {
        InvoiceInput()
    }
}
