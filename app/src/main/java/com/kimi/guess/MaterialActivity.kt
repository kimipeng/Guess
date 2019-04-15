package com.kimi.guess

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {


    val TAG = MaterialActivity::class.java.simpleName

    val secretNumber = SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle("Replay game")
                .setMessage("Are you sure?")
                .setPositiveButton(getString(R.string.ok), { dialog, which ->
                    secretNumber.reset()
                    counter.setText(secretNumber.count.toString())
                    ed_number.setText("")
                })
                .setNeutralButton("Cancel", null)
                .show()
        }

        counter.setText(secretNumber.count.toString())
        Log.d(TAG, "Secret: ${secretNumber.secret} ")
    }

    fun check(view: View) {

        if (TextUtils.isEmpty(ed_number.text)) return // 檢查數字是否空白，沒檢查會引起NumberFormatException

        val n = ed_number.text.toString().toInt()
        Log.d(TAG, "number: $n ")

        val diff = secretNumber.validate(n)
        var message = getString(R.string.yes_you_got_it)


        if (diff < 0) {
            message = getString(R.string.bigger)
        } else if (diff > 0) {
            message = getString(R.string.smaller)
        } else {
            if (secretNumber.count < 3) {
                message = getString(R.string.excellent_message) + secretNumber.secret
            }
        }

        counter.setText(secretNumber.count.toString())
        //Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), null)
            .show()

    }


}
