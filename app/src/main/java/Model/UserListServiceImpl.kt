package Model

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

    fun getUserList(page:Int, pageCount: Int) = userListService.getUserList("Bearer 582d3f45ff59c48c5dfb5454f894713a17a903c921873fbd1ffc6af32d9b1c1b",
            page,pageCount)

}