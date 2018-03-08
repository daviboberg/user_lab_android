package com.ebanx.circle

/**
 * Created by padovag on 08/03/2018.
 */


import android.content.Context
import org.json.JSONException
import org.json.JSONObject


class User(
        val id: String,
        val first_name: String,
        val last_name: String,
        //val email: String,
        val job_title: String,
        var imageUrl: String) {

    companion object {

        fun getUsersFromFile(filename: String, context: Context): ArrayList<User> {
            val userList = ArrayList<User>()

            try {
                // Load data
                val jsonString = loadJsonFromAsset("users.json", context)
                val json = JSONObject(jsonString)
                val users = json.getJSONArray("users")

                // Get Recipe objects from data
                (0 until users.length()).mapTo(userList) {
                    User(users.getJSONObject(it).getString("id"),
                            users.getJSONObject(it).getString("first_name"),
                            users.getJSONObject(it).getString("last_name"),
                            users.getJSONObject(it).getString("email"),
                            users.getJSONObject(it).getString("job_title"))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return userList
        }

        private fun loadJsonFromAsset(filename: String, context: Context): String? {
            var json: String? = null

            try {
                val inputStream = context.assets.open(filename)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, Charsets.UTF_8)
            } catch (ex: java.io.IOException) {
                ex.printStackTrace()
                return null
            }

            return json
        }
    }
}