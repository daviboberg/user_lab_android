package com.ebanx.circle

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    fun login(view: View) {
        val toastTest = Toast.makeText(this, "funciona", Toast.LENGTH_SHORT)
        toastTest.show()

        val username:String = LoginText.text.toString()
        val password:String = PasswordText.text.toString()

        // On success. Exemple of start activity
        //var userProfile = Intent(this, UserProfileActivity::class.java)
        //startActivity(userProfile)
    }
}
