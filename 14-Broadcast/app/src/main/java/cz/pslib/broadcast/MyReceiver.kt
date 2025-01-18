package cz.pslib.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(
            context,
            "Přijata zpráva po 2 sekundách",
            Toast.LENGTH_SHORT
        ).show()
    }
}