package com.ebanx.circle

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_user_list.*



/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [UserListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [UserListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserListFragment : Fragment() {

    private lateinit var imageResIds: IntArray
    private lateinit var names: Array<String>
    private lateinit var descriptions: Array<String>
    //private lateinit var listener: OnRageComicSelected

    private lateinit var linearLayoutManager: LinearLayoutManager

    // TODO: Rename and change types of parameters
    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayoutManager = LinearLayoutManager(context)
        user_list_recycler_view.layoutManager = linearLayoutManager

        val recyclerView = user_list_recycler_view
        recyclerView.adapter = UserListAdapter(names, context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_user_list, container,
                false)
        val activity = activity
        val recyclerView = user_list_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(activity)
        //recyclerView.adapter = RageComicAdapter(activity)
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        val resources = context!!.resources
        names = resources.getStringArray(R.array.names)

        // Get rage face images.
        val typedArray = resources.obtainTypedArray(R.array.images)
        val imageCount = names.size
        imageResIds = IntArray(imageCount)
        for (i in 0..imageCount - 1) {
            imageResIds[i] = typedArray.getResourceId(i, 0)
        }
        typedArray.recycle()
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    internal inner class UserListAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {


        private val layoutInflater: LayoutInflater

        init {
            layoutInflater = LayoutInflater.from(context)
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        }

        override fun getItemCount(): Int {
            return names.size
        }
    }

    internal inner class ViewHolder constructor(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

        /*
        fun setData() {
            user_list_recycler_view
        }
        */
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserListFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): UserListFragment {
            val fragment = UserListFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
