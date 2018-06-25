package proficiency.android.com.ui.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import proficiency.android.com.di.executor.ListDataExecutor
import proficiency.android.com.di.executor.entitymodel.ListDataModel
import proficiency.android.com.di.executor.entitymodel.ListDataRowModel
import proficiency.android.com.di.executor.interactor.ListModelListener
import proficiency.android.com.ui.R
import proficiency.android.com.ui.adapter.ListDataAdapter
import proficiency.android.com.ui.constants.ListConstants
import android.app.Activity
import android.widget.ListView
import android.os.AsyncTask.execute
import android.os.Parcel


/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment(), ListModelListener {

    private var listRowAdapter: ListDataAdapter? = null
    var listRowModel:List<ListDataRowModel>? = null
    private var mListener: OnFragmentInteractionListener? = null


    companion object {
        fun  newInstance():MainActivityFragment {
            val mainActivityFragment = MainActivityFragment();
            return mainActivityFragment;
        }
    }

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
        listRowModel = ArrayList()
        listRowAdapter=  ListDataAdapter(listRowModel)
        val mExecutor = ListDataExecutor()
        mExecutor.getListData(this)
        mRecyclerView.adapter = listRowAdapter
        return view;
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        try {
            mListener = activity as OnFragmentInteractionListener?
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString() + " must implement OnFragmentInteractionListener")
        }

    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    override fun onListDataAvailable(listDataModel: ListDataModel?) {
        if (mListener != null) {
            if (listDataModel != null) {
                mListener!!.onFragmentInteraction(listDataModel.getmTitle())
            };
        }
        listRowModel = listDataModel?.getListData()
        listRowAdapter!!.dataModelList = listRowModel
        listRowAdapter!!.notifyDataSetChanged()
    }

    override fun onError(message: String?) {
        fab.setOnClickListener { view ->
            Snackbar.make(view, R.string.error_message, Snackbar.LENGTH_LONG)
                    .setAction(R.string.retry, null).show()
        }
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(title: String)
    }

}

