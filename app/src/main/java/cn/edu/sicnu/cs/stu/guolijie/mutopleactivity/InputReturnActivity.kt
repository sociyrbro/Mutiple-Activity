package cn.edu.sicnu.cs.stu.guolijie.mutopleactivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cn.edu.sicnu.cs.stu.guolijie.mutopleactivity.ui.theme.MutopleActivityTheme

class InputReturnActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MutopleActivityTheme {
                InputReturnScreen(
                    onConfirm = { value ->
                        setResult(
                            RESULT_OK,
                            Intent().putExtra(EXTRA_RETURNED_TEXT, value)
                        )
                        finish()
                    }
                )
            }
        }
    }

    companion object {
        const val EXTRA_RETURNED_TEXT = "returned_text"
    }
}

@Composable
private fun InputReturnScreen(onConfirm: (String) -> Unit) {
    var input by rememberSaveable { mutableStateOf("") }

    ActivityPage(
        title = stringResource(R.string.input_title),
        subtitle = stringResource(R.string.input_subtitle)
    ) {
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.input_label)) },
            placeholder = { Text(stringResource(R.string.input_placeholder)) },
            singleLine = false,
            minLines = 4
        )
        FullWidthButton(
            text = stringResource(R.string.confirm_return),
            onClick = { onConfirm(input.trim()) },
            enabled = input.isNotBlank()
        )
    }
}
