package com.ebanx.circle

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView


class ListViewActivity : AppCompatActivity() {
    private lateinit var listView: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        listView = findViewById<ListView>(R.id.user_list_view)

        val userList = User.getUsersFromFile("users.json", this)

        val adapter = UserAdapter(this, userList)
        listView.adapter = adapter

        val context = this

//        listView.setOnItemClickListener { _, _, position, _ ->
//            val selectedRecipe = recipeList[position]
//
//            val detailIntent = RecipeDetailActivity.newIntent(context, selectedRecipe)
//
//            startActivity(detailIntent)
//        }
    }
}

