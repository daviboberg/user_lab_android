package Model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by leonardoapiovezan on 07/03/18.
 */
class AuthServiceImpl {

    private val authService: AuthService
    init {

        val retrofit = Retrofit.Builder()
                .baseUrl("https://app.pingboard.com/oauth/")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        authService = retrofit.create(AuthService::class.java)

    }

    fun authenticate(username:String,password:String):Single<AuthenticationDataResponse>{
        return authService.authenticate(
                username,
                password
        )
    }
}