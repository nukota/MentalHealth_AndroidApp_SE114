package com.example.uplift.ui.composables

import android.content.Context
import android.content.Intent
import android.net.Uri

fun SendEmail(context: Context, senderEmail: String, receiverEmail: String) {
    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(receiverEmail))
        putExtra(Intent.EXTRA_SUBJECT, "Request for more information from Uplift app")
        putExtra(Intent.EXTRA_TEXT, "Dear Specialist,\n\nI have got your information from Uplift app and would like to discuss more." +
                "\nPlease contact me at $senderEmail")
    }

    if (emailIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(Intent.createChooser(emailIntent, "Choose an Email client"))
    }
    else
        android.widget.Toast.makeText(context, "No email app found", android.widget.Toast.LENGTH_SHORT).show()
}
