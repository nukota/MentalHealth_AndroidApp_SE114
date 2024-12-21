package com.example.uplift.ui.composables

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log

@SuppressLint("QueryPermissionsNeeded")
fun sendEmail(context: Context, senderEmail: String, receiverEmail: String) {
    val emailIntent = Intent(Intent.ACTION_SEND).apply {
//        data = Uri.parse("mailto:")
        type = "message/rfc822"
        putExtra(Intent.EXTRA_EMAIL, arrayOf(receiverEmail))
        putExtra(Intent.EXTRA_SUBJECT, "Request for more information from Uplift app")
        putExtra(
            Intent.EXTRA_TEXT,
            "Dear Specialist,\n\nI have got your information from Uplift app and would like to discuss more." +
                    "\nPlease contact me at $senderEmail"
        )
    }
    Log.d("SendEmail", "Intent data: ${emailIntent.data}")
    Log.d("SendEmail", "Intent extras: ${emailIntent.extras}")


    if (emailIntent.resolveActivity(context.packageManager) != null) {
        Log.d("SendEmail", "Email client found, launching intent")
        context.startActivity(Intent.createChooser(emailIntent, "Choose an Email client"))
    } else {
        Log.d("SendEmail", "No email sent OO${senderEmail}OO and OO${receiverEmail}OO")
        android.widget.Toast.makeText(context, "No email app found", android.widget.Toast.LENGTH_SHORT).show()
    }
}
