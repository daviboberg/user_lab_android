package Model

import com.ebanx.circle.TokenManager
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
                .baseUrl("https://app.pingboard.com/api/v2/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        userService = retrofit.create(UserService::class.java)

    }

    fun getUser():Single<UserResponse>{
        return userService.getUserWith("Bearer ${TokenManager.instance.authenticationData?.access_token}")
    }
}