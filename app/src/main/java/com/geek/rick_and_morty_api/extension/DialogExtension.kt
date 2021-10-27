package com.geek.rick_and_morty_api.extension

import android.app.AlertDialog
import android.content.Context
import com.geek.rick_and_morty_api.R


fun Context.errorDialog(code: String, msg: String) {
    AlertDialog.Builder(this)
        .setIcon(R.drawable.ic_error_24)
        .setTitle(this.getString(R.string.error_title, code))
        .setMessage(msg)
        .setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }.show()
}