package Model

import com.ebanx.circle.TokenManager
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by leonardoapiovezan on 08/03/18.
 */
class UserListServiceImpl {

    private val userListService: UserListService

    init {

        val retrofit = Retrofit.Builder()
                .baseUrl("https://app.pingboard.com/api/v2/")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        userListService = retrofit.create(UserListService::class.java)

    }

    fun getUserList(page:Int, pageCount: Int) = userListService.getUserList("Bearer ${TokenManager.instance.authenticationData?.access_token}",
            page,pageCount)

}