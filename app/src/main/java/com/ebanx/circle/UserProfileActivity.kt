package com.ebanx.circle

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_user_profile.*
import Model.AuthServiceImpl
import Model.UserServiceImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class UserProfileActivity : AppCompatActivity() {


    var disposable:Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        setSupportActionBar(toolbar)

       // getUser()
    }

    fun getUser(){

        val userService = UserServiceImpl()

        val userObservable = userService.getUser()

        userObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->

                    println("Deu Boa")
                    println(response.users.email)

                }, { error ->

                    println("Deu ruim")
                    println(error.message)
                })

    }

    fun getToken(){

        println("Pegando o token")
        val authService = AuthServiceImpl()

        val authObservable = authService.authenticate("leonardo.piovezan@ebanx.com","senbonzakura1960")







        disposable = authObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->

                    println("Deu boa")
            println(response.access_token)

        }, {error ->
                    println("Deu ruim")
            println(error.message)
        })



    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

}
