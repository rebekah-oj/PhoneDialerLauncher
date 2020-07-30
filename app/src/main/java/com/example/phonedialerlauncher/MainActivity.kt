package com.example.phonedialerlauncher

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText_main)
        editText.setOnEditorActionListener { _, _, _ -> TODO() }
    }

    fun onEditorAction(textView: TextView?, actionId: Int, keyEvent: KeyEvent?): Boolean {
        var handled = false
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            dialNumber()
            handled = true
        }
        return handled
    }

    private fun dialNumber() {
        // Find the editText_main view.
        val editText: EditText = findViewById(R.id.editText_main)
        var phoneNum: String? = null
        // If the editText field is not null,
        // concatenate "tel: " with the phone number string.
        if (editText != null) phoneNum = "tel:" +
                editText.text.toString()
        // Specify the intent.
        val intent = Intent(Intent.ACTION_DIAL)
        // Set the data for the intent as the phone number.
        intent.data = Uri.parse(phoneNum)
        // If the intent resolves to a package (app),
        // start the activity with the intent.
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this!")
        }
    }
}