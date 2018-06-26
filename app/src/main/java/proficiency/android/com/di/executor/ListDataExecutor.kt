package proficiency.android.com.di.executor

import android.arch.persistence.room.Room
import android.content.Context

import proficiency.android.com.data.ListDataListener
import proficiency.android.com.data.errors.NetworkErrorParser
import proficiency.android.com.data.local.db.AppDatabase
import proficiency.android.com.data.local.db.AppDbHelper
import proficiency.android.com.data.model.api.ListDataResponse
import proficiency.android.com.data.model.db.ListData
import proficiency.android.com.data.remote.providers.ListDataProvider
import proficiency.android.com.di.executor.ListDataFactory.LisDataFactory
import proficiency.android.com.di.executor.interactor.ListModelListener
import proficiency.android.com.ui.constants.ListConstants
import proficiency.android.com.util.ListDataUtil
import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * This class serves as an interface between UI and data layer.
 * The UI does not need to bother about the underlying data part and vice versa
 */
class ListDataExecutor(private val context: Context) {

    private val subscriptions: CompositeSubscription

    init {
        this.subscriptions = CompositeSubscription()
    }

    /*API for getting list data details. Save the responses to the database*/

    fun getListData(listModelListener: ListModelListener) {
        val listDataProvider = ListDataProvider()
        val subscription = listDataProvider.getListDataFromServer(object : ListDataListener {
            override fun onCompleteLoading(listDataResponse: ListDataResponse) {
                saveData(listDataResponse, listModelListener)
            }

            override fun onError(message: String) {
                listModelListener.onError(message)
            }
        })

        subscriptions.add(subscription)
    }

    fun onStop() {
        subscriptions.unsubscribe()
    }

    /**
     * Save the response to database. Only the list data will be cached.
     * @param listDataResponse: Response
     * @param listModelListener: Listener
     */
    private fun saveData(listDataResponse: ListDataResponse, listModelListener: ListModelListener) {
        val db = Room.databaseBuilder(context,
                AppDatabase::class.java, ListConstants.DB_NAME).build()
        val subscription = AppDbHelper(db).saveListDataList(listDataResponse.listData).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Boolean>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        listModelListener.onError(e.message)

                    }

                    override fun onNext(aBoolean: Boolean?) {
                        val lisDataFactory = LisDataFactory()
                        lisDataFactory.generateListData(listDataResponse, listModelListener)
                    }
                })
        subscriptions.add(subscription)
    }

    /**
     * If offline get data from the database
     * @param listModelListener: listeners
     */
    fun getDataFromDB(listModelListener: ListModelListener) {
        val db = Room.databaseBuilder(context,
                AppDatabase::class.java, ListConstants.DB_NAME).build()
        val subscription = AppDbHelper(db).allListData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<List<ListData>>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        listModelListener.onError(e.message)

                    }

                    override fun onNext(listData: List<ListData>) {
                        val lisDataFactory = LisDataFactory()
                        lisDataFactory.generateListDataDb(listData, listModelListener)
                    }
                })
        subscriptions.add(subscription)
    }

}
