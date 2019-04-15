package com.kimi.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName

    val secretNumber = SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "secret: " + secretNumber.secret)

    }


    fun check(view: View) {
        val n = ed_number.text.toString().toInt()
        Log.d(TAG, " number: $n ")

        val diff = secretNumber.validate(n)
        var message = getString(R.string.yes_you_got_it)


        if (diff < 0) {
            message = getString(R.string.bigger)
        } else if (diff > 0) {
            message = getString(R.string.smaller)
        }
        //Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), null)
            .show()

    }

}
