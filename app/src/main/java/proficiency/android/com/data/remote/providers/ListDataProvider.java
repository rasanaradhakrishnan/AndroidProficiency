package proficiency.android.com.data.remote.providers;

import proficiency.android.com.data.ListDataListener;
import proficiency.android.com.data.errors.NetworkErrorParser;
import proficiency.android.com.data.model.api.ListDataResponse;
import proficiency.android.com.data.remote.ServiceCall;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class ListDataProvider {

    public  Subscription getListDataFromServer(final ListDataListener listDataListener) {

        Retrofit retrofit;


        return new ServiceCall().getNetWorkService().getProfile() .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends ListDataResponse>>() {
                    @Override
                    public Observable<? extends ListDataResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<ListDataResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listDataListener.onError(new NetworkErrorParser(e).getNetworkError().getMessage());

                    }

                    @Override
                    public void onNext(ListDataResponse listDataResponse) {
                        listDataListener.onCompleteLoading(listDataResponse);

                    }
                });
    }
}
