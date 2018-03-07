package com.ebanx.circle

import Model.AuthServiceImpl
import Model.AuthenticationDataResponse
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {

    var disposable: Disposable? = null

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


        val username:String = LoginText.text.toString()
        val password:String = PasswordText.text.toString()

        val authService = AuthServiceImpl()

        val authObservable = authService.authenticate(username, password)

        disposable = authObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->

                    // temporary activity, should go to list users
                    startActivity(Intent(this, UserProfileActivity::class.java))

                }, { error ->
                    val toastTest = Toast.makeText(this, "Invalid Username or Login", Toast.LENGTH_SHORT)
                    toastTest.show()
                })

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}
