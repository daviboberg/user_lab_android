package Model


/**
 * Created by leonardoapiovezan on 07/03/18.
 */


import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @POST("token?grant_type=password")
    fun authenticate(@Query("username") username: String,
                     @Query("password") password: String):Single<AuthenticationDataResponse>
}

class AuthenticationDataResponse(
        val access_token:String,
        val token_Type:String,
        val expires_in: Int,
        val refresh_token: String,
        val scope: String,
        val created_at: Int
)
