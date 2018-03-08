package com.ebanx.circle

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

class BottomNavigationActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.lista -> {
                displayUserListFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile -> {
                displayUserProfileFragment()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    val userListFragment = UserListFragment()
    val userProfileFragment = UserProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        displayUserListFragment()
    }

    fun displayUserListFragment() {
        val transaction = supportFragmentManager.beginTransaction()

        if (userListFragment.isAdded){
            transaction.show(userListFragment)
        } else {
            transaction.add(R.id.container,userListFragment,"UserList")
        }
        if (userProfileFragment.isAdded){
            transaction.hide(userProfileFragment)
        }
        transaction.commit()
    }

    fun displayUserProfileFragment() {

        val transaction = supportFragmentManager.beginTransaction()

        if (userProfileFragment.isAdded){
            transaction.show(userProfileFragment)
        } else {
            transaction.add(R.id.container,userProfileFragment,"UserList")
        }
        if (userListFragment.isAdded){
            transaction.hide(userListFragment)
        }
        transaction.commit()
    }

  //  private  fun startFragmentTransaction(){




//      if  (supportFragmentManager.fragments.count() <= 0 ){
//
//
//          transaction.commit()
//          return
//
//      }
//        if (userListFragment.isVisible) {
//
//            transaction.hide(userListFragment)
//            transaction.add(R.id.container,userProfileFragment)
//            transaction.show(userProfileFragment)
//            transaction.commit()
//        } else {
//            transaction.hide(userProfileFragment)
//            transaction.add(R.id.container,userListFragment)
//            transaction.show(userListFragment)
//            transaction.commit()
//        }
//    }
//    private fun startFragmentTransaction(fragment: Fragment) {
//        supportFragmentManager.beginTransaction()
//                .replace(R.id.container, fragment)
//                .commit()
//    }
}
