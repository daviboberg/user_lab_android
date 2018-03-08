package com.ebanx.circle


import Model.UserDataResponse
import Model.UserServiceImpl
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_evaluate_profile.*


class UserProfileFragment : Fragment() {

    private var mListener: OnFragmentInteractionListener? = null

    private var disposable:Disposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getUser()

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        return inflater!!.inflate(R.layout.fragment_evaluate_profile, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }


    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): UserProfileFragment {
            val fragment = UserProfileFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
    fun getUser(){

        val userService = UserServiceImpl()

        val userObservable = userService.getUser()

        val subscribe = userObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response ->

                            println("Deu Boa")
                           setUpViewWith(response.users.first())

                        },
                        { error ->

                            println("Deu ruim")
                            println(error.message)
                        }
                )

        disposable = subscribe

    }

    fun setUpViewWith(user: UserDataResponse){


        userProfileNameTextView.text = user.first_name + " " + user.last_name
        userProfileEmailTextView.text = user.email
        userProfileJobTextView.text = user.job_title
        userProfileTeamTextView.text = "Time"

        Picasso.with(context)
                .load(user.avatar_urls.medium)
                .into(userProfileImageView)

    }

    override fun onStop() {
        if (activity.isFinishing){
            disposable?.dispose()
        }
        super.onStop()
    }
}
