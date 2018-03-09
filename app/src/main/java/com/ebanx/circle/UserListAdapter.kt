package com.ebanx.circle

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import android.support.v7.widget.RecyclerView.Adapter
import com.ebanx.circle.R.array.names
import com.ebanx.circle.R.layout.fragment_user_item
import kotlinx.android.synthetic.main.fragment_user_item.*
import kotlinx.android.synthetic.main.fragment_user_item.view.*
import kotlinx.android.synthetic.main.fragment_user_list.view.*

class UserListAdapter(private val names: Array<String>,
                      private val context: Context) : Adapter<UserListAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val name = names[position]
        if (holder != null) {
            holder.title.text = name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_user_item, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.itemName

        fun bindView(name: String) {
            val title = itemView.itemName

            title.text = name
        }
    }
}