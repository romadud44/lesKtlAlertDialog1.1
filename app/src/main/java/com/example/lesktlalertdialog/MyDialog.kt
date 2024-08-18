package com.example.lesktlalertdialog

import android.content.Context
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MyDialog {
    companion object {
        fun createDialog(context: Context, adapter: ArrayAdapter<User>) =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Внимание!")
                    .setMessage("Удалить пользователя?")
                    .setCancelable(true)
                    .setNegativeButton("Нет") { dialog, _ ->
                        dialog.cancel()
                    }
                    .setPositiveButton("Да") { _, _ ->
                        val user = adapter.getItem(position)
                        adapter.remove(user)

                        Toast.makeText(context, "Удален пользоваетль: $user", Toast.LENGTH_LONG)
                            .show()
                    }.create()
                builder.show()
            }
    }
}