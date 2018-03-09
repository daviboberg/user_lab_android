package com.ebanx.circle

import Model.AuthServiceImpl
import Model.AuthenticationDataResponse
import Model.TokenDataManager
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.squareup.moshi.Moshi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*
import org.w3c.dom.Text
import retrofit2.converter.moshi.MoshiConverterFactory
class LoginActivity : AppCompatActivity() {

    private var disposable: Disposable? = null
    private var tokenDataManager:TokenDataManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

        tokenDataManager = TokenDataManager(applicationContext)
        LoginText.setText(getEmail())
    }

    fun login(view: View) {
        val username:String = LoginText.text.toString()
        val password:String = PasswordText.text.toString()

        val authService = AuthServiceImpl()

        val authObservable = authService.authenticate(username, password)

        disposable = authObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response ->

                            startActivity(Intent(this, BottomNavigationActivity::class.java))
                            TokenManager.instance.authenticationData = response
                            save(username)
                            tokenDataManager?.transformToJson(response)

                        },
                        { error ->
                            val toastTest = Toast.makeText(this, "Invalid Username or Login", Toast.LENGTH_SHORT)
                            toastTest.show()
                        }
                )
    }

    override fun onStop() {
        if (isFinishing) {
            disposable?.dispose()
        }
        super.onStop()
    }

    private fun save(email:String){
        tokenDataManager?.save("email",email)
    }

    private fun getEmail() = tokenDataManager?.read("email")

}
