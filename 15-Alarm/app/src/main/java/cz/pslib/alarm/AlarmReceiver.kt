package cz.pslib.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.v("AlarmReceiver", "Alarm received!")
        Toast.makeText(context, "Alarm!!!!!!", Toast.LENGTH_SHORT).show()
    }
}