package Model


/**
 * Created by leonardoapiovezan on 07/03/18.
 */


import android.content.Context
import com.squareup.moshi.Moshi
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @POST("token?grant_type=password")
    fun authenticate(@Query("username") username: String,
                     @Query("password") password: String):Single<AuthenticationDataResponse>
}

//class AuthenticationResponse(val data: AuthenticationDataResponse)

class AuthenticationDataResponse(
        var access_token:String,
        var token_Type:String,
        var expires_in: Int,
        var refresh_token: String,
        var scope: String,
        var created_at: Int
)