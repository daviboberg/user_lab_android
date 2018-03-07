package Model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by leonardoapiovezan on 07/03/18.
 */
class UserServiceImpl {


    private val userService: UserService

    init {


        val retrofit = Retrofit.Builder()
                .baseUrl("https://app.pingboard.com/oauth/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        userService = retrofit.create(UserService::class.java)

    }

    fun getUser():Single<UserResponse>{
        return userService.getUserWith("cd5e5f5839d0518f6bc9e31605a3eff4de89b1c7d21dda1061eda41fe7117796")
    }
}