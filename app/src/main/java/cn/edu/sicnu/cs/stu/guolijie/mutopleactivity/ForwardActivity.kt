package cn.edu.sicnu.cs.stu.guolijie.mutopleactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cn.edu.sicnu.cs.stu.guolijie.mutopleactivity.ui.theme.MutopleActivityTheme

class ForwardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val receivedContent = intent.getStringExtra(EXTRA_FORWARD_CONTENT).orEmpty()

        setContent {
            MutopleActivityTheme {
                ForwardScreen(receivedContent)
            }
        }
    }
}

@Composable
private fun ForwardScreen(receivedContent: String) {
    ActivityPage(
        title = stringResource(R.string.forward_title),
        subtitle = stringResource(R.string.forward_subtitle)
    ) {
        InfoSurface {
            Text(
                text = stringResource(R.string.forward_received_label),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = receivedContent,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.forward_value_source),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
