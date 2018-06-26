package proficiency.android.com.di.executor;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import proficiency.android.com.data.ListDataListener;
import proficiency.android.com.data.errors.NetworkErrorParser;
import proficiency.android.com.data.local.db.AppDatabase;
import proficiency.android.com.data.local.db.AppDbHelper;
import proficiency.android.com.data.model.api.ListDataResponse;
import proficiency.android.com.data.model.db.ListData;
import proficiency.android.com.data.remote.providers.ListDataProvider;
import proficiency.android.com.di.executor.ListDataFactory.LisDataFactory;
import proficiency.android.com.di.executor.interactor.ListModelListener;
import proficiency.android.com.ui.constants.ListConstants;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ListDataExecutor {

    private CompositeSubscription subscriptions;
    private Context context;

    public ListDataExecutor(Context context) {
        this.context = context;
        this.subscriptions = new CompositeSubscription();
    }

    /*API for getting cart details*/

    public void getListData(final ListModelListener listModelListener){
        ListDataProvider listDataProvider = new ListDataProvider();
        Subscription subscription = listDataProvider.getListDataFromServer(new ListDataListener() {
            @Override
            public void onCompleteLoading(ListDataResponse listDataResponse) {
                saveData(listDataResponse, listModelListener);
            }

            @Override
            public void onError(String message) {
                onStop();
                listModelListener.onError(message);
            }
        });

        subscriptions.add(subscription);
    }

    private void onStop() {
        subscriptions.unsubscribe();
    }

    private void saveData(final ListDataResponse listDataResponse, final ListModelListener listModelListener){
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, ListConstants.Companion.getDB_NAME()).build();
        Subscription subscription = new AppDbHelper(db).saveListDataList(listDataResponse.getListData()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onStop();
                        listModelListener.onError(e.getMessage());

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        LisDataFactory lisDataFactory = new LisDataFactory();
                        lisDataFactory.generateListData(listDataResponse,listModelListener);
                        onStop();
                    }
                });
        subscriptions.add(subscription);
    }

    private void getDataFromDB(final ListModelListener listModelListener){
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, ListConstants.Companion.getDB_NAME()).build();
        Subscription subscription = new AppDbHelper(db).getAllListData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ListData>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onStop();
                        listModelListener.onError(e.getMessage());

                    }

                    @Override
                    public void onNext(List<ListData> listData) {
                        LisDataFactory lisDataFactory = new LisDataFactory();
                        lisDataFactory.generateListDataDb(listData,listModelListener);
                        onStop();
                    }
                });
        subscriptions.add(subscription);
    }

}
