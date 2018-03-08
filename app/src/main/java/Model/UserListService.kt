package Model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Created by leonardoapiovezan on 08/03/18.
 */
interface UserListService {

    @GET("users")
    fun getUserList(@Header("Authorization") accessToken:String,@Query("page") page: Int,
                    @Query("page_size") pageSize: Int):Single<UserListResponse>

}

class UserListResponse(val users: List<UserDataResponse>, val meta:MetaData)

class MetaData(val users: UserListPagination)

class UserListPagination(
        val page:Int,
        val page_size:Int,
        val count:Int,
        val page_count:Int
)
