package com.ebanx.circle

import Model.AuthServiceImpl
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_user_profile.*
import Model.UserDataResponse
import Model.UserListServiceImpl
import Model.UserServiceImpl
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_user_profile.*


class UserProfileActivity : AppCompatActivity() {


    private var disposable:Disposable? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        setSupportActionBar(toolbar)


        getUser()

       // getToken()
//        getUser()

        //getUserList()


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

    fun setUpViewWith(user:UserDataResponse) {
        nameTextView.text = user.first_name + " " + user.last_name
        emailTextView.text = user.email
        jobTextView.text = user.job_title
        teamTextView.text = "time"
        Picasso.with(this.baseContext)
                .load(user.avatar_urls.medium)
                .into(userProfileImageView)
    }

    override fun onStop() {
        if (isFinishing){
            disposable?.dispose()
        }
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }


    fun getUserList(){

        val userListService = UserListServiceImpl()

        val userListObservable = userListService.getUserList(1,100)

        disposable = userListObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->

                    println("Deu boa de pegar a lista de usuários")
                    println(response.users.count())
                    println(response.meta.users.page_count)




                }, {error ->

                    println("Deu ruim de pegar a lista de usuários")

                    println(error.message)
                })
    }


    fun getToken(){

        val username:String = ""
        val password:String = ""

        val authService = AuthServiceImpl()

        val authObservable = authService.authenticate(username, password)

        disposable = authObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->

                    println("Deu boa")
                   println(response.access_token)

                }, { error ->

                    println("Deu ruim")
                    println(error.message)
                })
    }

}
