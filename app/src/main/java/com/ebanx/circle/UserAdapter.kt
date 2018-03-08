
package com.ebanx.circle

/**
 * Created by padovag on 08/03/2018.
 */


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso



class UserAdapter (private val context: Context,
                     private val dataSource: ArrayList<User>) : BaseAdapter() {
    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

//    companion object {
//        private val LABEL_COLORS = hashMapOf(
//                "Low-Carb" to R.color.colorLowCarb,
//                "Low-Fat" to R.color.colorLowFat,
//                "Low-Sodium" to R.color.colorLowSodium,
//                "Medium-Carb" to R.color.colorMediumCarb,
//                "Vegetarian" to R.color.colorVegetarian,
//                "Balanced" to R.color.colorBalanced
//        )
//    }

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val holder: ViewHolder

        // 1
        if (convertView == null) {

            // 2
            view = inflater.inflate(R.layout.content_list_item_actitivy, parent, false)

            // 3
            holder = ViewHolder()
            holder.thumbnailImageView = view.findViewById<ImageView>(R.id.userImageView)
            holder.titleTextView = view.findViewById<TextView>(R.id.userNameTextView)
            holder.subtitleTextView = view.findViewById<TextView>(R.id.userTitleTextView)

            // 4
            view.tag = holder
        } else {
            // 5
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        // 6
        val titleTextView = holder.titleTextView
        val subtitleTextView = holder.subtitleTextView
        val detailTextView = holder.detailTextView
        val thumbnailImageView = holder.thumbnailImageView

        val user = getItem(position) as User

        titleTextView.text = user.first_name + user.last_name
        subtitleTextView.text = user.job_title

        Picasso.with(context).load(user.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)

//        val titleTypeFace = ResourcesCompat.getFont(context, R.font.josefinsans_bold)
//        titleTextView.typeface = titleTypeFace
//
//        val subtitleTypeFace = ResourcesCompat.getFont(context, R.font.josefinsans_semibolditalic)
//        subtitleTextView.typeface = subtitleTypeFace
//
//        val detailTypeFace = ResourcesCompat.getFont(context, R.font.quicksand_bold)
//        detailTextView.typeface = detailTypeFace
//
//        detailTextView.setTextColor(
//                ContextCompat.getColor(context, LABEL_COLORS[recipe.label] ?: R.color.colorPrimary))

        return view
    }

    private class ViewHolder {
        lateinit var titleTextView: TextView
        lateinit var subtitleTextView: TextView
        lateinit var detailTextView: TextView
        lateinit var thumbnailImageView: ImageView
    }
}