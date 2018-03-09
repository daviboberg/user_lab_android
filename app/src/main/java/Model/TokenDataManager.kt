package Model

import android.content.Context
import com.squareup.moshi.Moshi

/**
 * Created by leonardoapiovezan on 09/03/18.
 */


class TokenDataManager(val context:Context):DataManager{

    private val preferences = context.getSharedPreferences("Preferences", 0)

    private  val editor = preferences.edit()

    override fun read(key: String): String {

        return preferences.getString(key,null)
    }

    override fun save(key: String, value: String) {

        editor.putString(key, value)
        editor.apply()
    }

    override fun remove(key: String) {
        editor.remove(key)
        editor.commit()

    }

    override fun removeAll() {
        editor.clear()
        editor.commit()
    }

    override fun save(key: String, value: Any) {

    }

    fun readAuthentication(): AuthenticationDataResponse? {
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(AuthenticationDataResponse::class.java)

        val objectString = this.read("AuthenticationDataResponse")

        return jsonAdapter.fromJson(objectString)

    }
    fun transformToJson(test:AuthenticationDataResponse){

        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(AuthenticationDataResponse::class.java)

        val json = jsonAdapter.toJson(test)

        this.save("AuthenticationDataResponse", json)

    }

}