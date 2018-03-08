package com.ebanx.circle

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_user_profile.*
import Model.UserDataResponse
import Model.UserServiceImpl
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_user_profile.*


class UserProfileActivity : AppCompatActivity() {


    var disposable:Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        setSupportActionBar(toolbar)


        getUser()

       // getToken()
    }

    fun getUser(){

        val userService = UserServiceImpl()

        val userObservable = userService.getUser()

        val subscribe = userObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response ->

                            println("Deu Boa")
                            setUpViewWith(response.users.first())

                        },
                        { error ->

                            println("Deu ruim")
                            println(error.message)
                        }
                )

        disposable = subscribe

    }


    fun setUpViewWith(user:UserDataResponse){


        nameTextView.text = user.first_name + " " + user.last_name
        emailTextView.text = user.email
        jobTextView.text = user.job_title
        teamTextView.text = "time"

        Picasso.with(this.baseContext)
                .load(user.avatar_urls.medium)
                .into(userProfileImageView)

    }


    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

}
