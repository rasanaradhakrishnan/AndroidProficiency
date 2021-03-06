package proficiency.android.com.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import proficiency.android.com.di.executor.ListDataExecutor
import proficiency.android.com.di.executor.entitymodel.ListDataModel
import proficiency.android.com.di.executor.entitymodel.ListDataRowModel
import proficiency.android.com.di.executor.interactor.ListModelListener
import proficiency.android.com.ui.R
import proficiency.android.com.ui.adapter.ListDataAdapter
import proficiency.android.com.util.ListDataUtil


/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment(), ListModelListener {

    private var listRowAdapter: ListDataAdapter? = null
    private var listRowModel:List<ListDataRowModel>? = null
    private var mListener: OnFragmentInteractionListener? = null
    private var pb:ProgressBar? = null
    private var mExecutor:ListDataExecutor?=null

    /**
     * This method will only be called once when the retained
     * Fragment is first created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retain this fragment across configuration changes.
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)
        val mRecyclerView: ListView = view.findViewById(R.id.list)
        val fab: FloatingActionButton = view.findViewById(R.id.fab)
        listRowModel = ArrayList()
        listRowAdapter=  ListDataAdapter(listRowModel)
        // show progress bar
        pb = view.findViewById(R.id.pbLoading)
        pb!!.visibility = ProgressBar.VISIBLE
        mExecutor = ListDataExecutor(activity!!.applicationContext)
        if (ListDataUtil.isConnected(activity!!.applicationContext)) {
            mExecutor!!.getListData(this)
        }else{
            mExecutor!!.getDataFromDB(this)
        }

        mRecyclerView.adapter = listRowAdapter

        fab.setOnClickListener { _ ->
            pb!!.visibility = ProgressBar.VISIBLE
            if (ListDataUtil.isConnected(activity!!.applicationContext)) {
                mExecutor!!.getListData(this)
            }else{
                mExecutor!!.getDataFromDB(this)
            }
        }
        return view
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        try {
            mListener = activity as OnFragmentInteractionListener?
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString())
        }

    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    override fun onListDataAvailable(listDataModel: ListDataModel?) {
        // run a background job and once complete
        pb!!.visibility = ProgressBar.INVISIBLE
        if (mListener != null) {
            if (listDataModel != null) {
                mListener!!.onFragmentInteraction(listDataModel.getmTitle())
            }
        }
        listRowModel = listDataModel?.listData
        listRowAdapter!!.dataModelList = listRowModel
        listRowAdapter!!.notifyDataSetChanged()
    }

    override fun onError(message: String?) {
        // run a background job and once complete
        pb!!.visibility = ProgressBar.INVISIBLE
        Snackbar.make(activity!!.findViewById(android.R.id.content),
                R.string.error_message, Snackbar.LENGTH_INDEFINITE).setAction(R.string.retry) {
                    pb!!.visibility = ProgressBar.VISIBLE
                    if (ListDataUtil.isConnected(activity!!.applicationContext)) {
                        mExecutor!!.getListData(this)
                    }else{
                        mExecutor!!.getDataFromDB(this)
                    }
                }.show()
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(title: String)
    }

}

