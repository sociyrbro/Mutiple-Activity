package cn.edu.sicnu.cs.stu.guolijie.mutopleactivity

import android.os.Bundle
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.edu.sicnu.cs.stu.guolijie.mutopleactivity.ui.theme.MutopleActivityTheme
import kotlinx.coroutines.delay

class StopwatchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MutopleActivityTheme {
                StopwatchScreen()
            }
        }
    }
}

@Composable
private fun StopwatchScreen() {
    var running by rememberSaveable { mutableStateOf(false) }
    var accumulatedMillis by rememberSaveable { mutableStateOf(0L) }
    var startedAtMillis by rememberSaveable { mutableStateOf(0L) }
    var nowMillis by remember { mutableLongStateOf(SystemClock.elapsedRealtime()) }

    LaunchedEffect(running) {
        while (running) {
            nowMillis = SystemClock.elapsedRealtime()
            delay(31)
        }
    }

    val elapsedMillis = accumulatedMillis + if (running && startedAtMillis > 0L) {
        nowMillis - startedAtMillis
    } else {
        0L
    }
    val totalSeconds = elapsedMillis / 1_000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    val centiseconds = (elapsedMillis % 1_000) / 10
    val timeText = stringResource(
        R.string.stopwatch_time_format,
        minutes.toInt(),
        seconds.toInt(),
        centiseconds.toInt()
    )

    ActivityPage(
        title = stringResource(R.string.stopwatch_title),
        subtitle = stringResource(R.string.stopwatch_subtitle)
    ) {
        Text(
            text = timeText,
            style = MaterialTheme.typography.displayMedium,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 56.sp
        )
        Text(
            text = stringResource(
                if (running) R.string.stopwatch_status_running else R.string.stopwatch_status_paused
            ),
            style = MaterialTheme.typography.titleMedium,
            color = if (running) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {
                    if (running) {
                        val current = SystemClock.elapsedRealtime()
                        accumulatedMillis += current - startedAtMillis
                        startedAtMillis = 0L
                        nowMillis = current
                        running = false
                    } else {
                        startedAtMillis = SystemClock.elapsedRealtime()
                        nowMillis = startedAtMillis
                        running = true
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(
                        if (running) R.string.stopwatch_pause else R.string.stopwatch_start
                    )
                )
            }
            Button(
                onClick = {
                    running = false
                    accumulatedMillis = 0L
                    startedAtMillis = 0L
                    nowMillis = SystemClock.elapsedRealtime()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(R.string.stopwatch_reset))
            }
        }

        InfoSurface {
            Text(
                text = stringResource(R.string.stopwatch_rotation_note),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
