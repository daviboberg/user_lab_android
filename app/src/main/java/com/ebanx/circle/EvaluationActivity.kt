package com.ebanx.circle

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_evaluation.*

class EvaluationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluation)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

//    listView = findViewById<ListView>(R.id.recipe_list_view)
//    // 1
//    val recipeList = Recipe.getRecipesFromFile("recipes.json", this)
//    // 2
//    val listItems = arrayOfNulls<String>(recipeList.size)
//// 3
//    for (i in 0 until recipeList.size) {
//        val recipe = recipeList[i]
//        listItems[i] = recipe.title
//    }
//    // 4
//    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
//    listView.adapter = adapter

}
