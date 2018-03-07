package Model
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by leonardoapiovezan on 07/03/18.
 */
interface UserService {

    @GET("users/me")
    fun getUserWith(@Header("Bearer ") accessToken:String):Single<UserResponse>


}


class UserResponse(val users: UserDataResponse)


class UserDataResponse(

        val id:String,
        val job_title:String,
        val first_name:String,
        val last_name:String,
        val email:String

)