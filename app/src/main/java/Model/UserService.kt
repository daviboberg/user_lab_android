package Model
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header


/**
 * Created by leonardoapiovezan on 07/03/18.
 */
interface UserService {

    @GET("users/me")

    fun getUserWith(@Header("Authorization") accessToken:String):Single<UserResponse>

}


class UserResponse(val users: List<UserDataResponse>)


class UserDataResponse(

        val id:String,
        val job_title:String,
        val first_name:String,
        val last_name:String,
        val email:String,
        val avatar_urls:PictureUrls
)

class PictureUrls(

        val icon:String,
        val large:String,
        val small:String,
        val medium:String,
        val original:String
)