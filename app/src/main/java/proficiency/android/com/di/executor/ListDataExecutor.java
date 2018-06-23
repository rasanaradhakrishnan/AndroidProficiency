package proficiency.android.com.di.executor;

import proficiency.android.com.data.ListDataListener;
import proficiency.android.com.data.model.api.ListDataResponse;
import proficiency.android.com.data.remote.providers.ListDataProvider;
import proficiency.android.com.di.executor.ListDataFactory.LisDataFactory;
import proficiency.android.com.di.executor.interactor.ListModelListener;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class ListDataExecutor {

    private CompositeSubscription subscriptions;

    public ListDataExecutor() {
        this.subscriptions = new CompositeSubscription();
    }

    /*API for getting cart details*/

    public void getListData(final ListModelListener listModelListener){
        ListDataProvider listDataProvider = new ListDataProvider();
        Subscription subscription = listDataProvider.getListDataFromServer(new ListDataListener() {
            @Override
            public void onCompleteLoading(ListDataResponse listDataResponse) {
                LisDataFactory lisDataFactory = new LisDataFactory();
                lisDataFactory.generateCartModel(listDataResponse,listModelListener);
            }

            @Override
            public void onError(String message) {
                listModelListener.onError(message);
            }
        });

        subscriptions.add(subscription);
    }
    public void onStop() {
        subscriptions.unsubscribe();
    }

}
