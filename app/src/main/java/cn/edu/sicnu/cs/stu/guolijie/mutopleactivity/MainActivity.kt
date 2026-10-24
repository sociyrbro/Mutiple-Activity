package cn.edu.sicnu.cs.stu.guolijie.mutopleactivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.edu.sicnu.cs.stu.guolijie.mutopleactivity.ui.theme.MutopleActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MutopleActivityTheme {
                MainScreen()
            }
        }
    }

    companion object {
        const val EXTRA_FORWARD_TEXT = "forward_text"
    }
}

@Composable
private fun MainScreen() {
    val context = LocalContext.current
    var returnedText by rememberSaveable {
        mutableStateOf(context.getString(R.string.main_returned_result_initial))
    }

    val returnLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == ComponentActivity.RESULT_OK) {
            val value = result.data?.getStringExtra(InputReturnActivity.EXTRA_RETURNED_TEXT)
            if (!value.isNullOrBlank()) {
                returnedText = value
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.assignment_title),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(R.string.main_subtitle),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                ActivityButton(
                    text = stringResource(R.string.main_button_forward),
                    onClick = {
                        context.startActivity(
                            Intent(context, ForwardActivity::class.java).apply {
                                putExtra(
                                    EXTRA_FORWARD_CONTENT,
                                    context.getString(R.string.student_summary)
                                )
                            }
                        )
                    }
                )
                ActivityButton(
                    text = stringResource(R.string.main_button_return),
                    onClick = {
                        returnLauncher.launch(
                            Intent(context, InputReturnActivity::class.java)
                        )
                    }
                )
                ActivityButton(
                    text = stringResource(R.string.main_button_camera),
                    onClick = {
                        context.startActivity(
                            Intent(context, CameraActivity::class.java)
                        )
                    }
                )
                ActivityButton(
                    text = stringResource(R.string.main_button_stopwatch),
                    onClick = {
                        context.startActivity(
                            Intent(context, StopwatchActivity::class.java)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = stringResource(R.string.main_returned_result_label),
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = returnedText,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ActivityButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = text, style = MaterialTheme.typography.titleMedium)
    }
}

const val EXTRA_FORWARD_CONTENT = "forward_content"
