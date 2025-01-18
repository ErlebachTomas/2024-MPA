package cz.pslib.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        setContent {
            AlarmScreen(
                onSetAlarm = { setAlarm(it) },
                onCancelAlarm = { cancelAlarm() }
            )
        }
    }

    private fun setAlarm(delay: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager.canScheduleExactAlarms()) {
                scheduleAlarm(delay)
            } else {
                Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).also {
                    it.data = Uri.parse("package:$packageName")
                    startActivity(it)
                }
                Toast.makeText(
                    this,
                    "Prosím, povolte přesné alarmy pro aplikaci",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            scheduleAlarm(delay)
        }
    }

    private fun scheduleAlarm(delay: Int) {
        val triggerTime = System.currentTimeMillis() + (delay * 1000)

        val intent = Intent(this, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            triggerTime,
            pendingIntent
        )

        Toast.makeText(
            this,
            "Alarm nastaven za $delay vteřin",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun cancelAlarm() {
        if (::pendingIntent.isInitialized) {
            alarmManager.cancel(pendingIntent)
            Toast.makeText(this, "Alarm zrušen", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Žádný alarm nenalezen", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun AlarmScreen(
    onSetAlarm: (Int) -> Unit,
    onCancelAlarm: () -> Unit
) {
    var minutes by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = minutes,
            onValueChange = { minutes = it },
            label = { Text("Čas v sekundách") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    minutes.toIntOrNull()?.let { onSetAlarm(it) }
                }
            ) {
                Text("Nastavit alarm")
            }

            Button(
                onClick = { onCancelAlarm() }
            ) {
                Text("Zrušit alarm")
            }
        }
    }
}
