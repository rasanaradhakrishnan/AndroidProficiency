package proficiency.android.com.data.remote.providers;

import android.content.Context;

import proficiency.android.com.data.ListDataListener;
import proficiency.android.com.data.errors.NetworkErrorParser;
import proficiency.android.com.data.local.db.AppDbHelper;
import proficiency.android.com.data.model.api.ListDataResponse;
import proficiency.android.com.data.remote.CustomCallback;
import proficiency.android.com.data.remote.ServiceCall;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ListDataProvider {

    public static void getListDataFromServer(final Context context, String url, final ListDataListener listDataListener) {

        Retrofit retrofit;
        Call<ListDataResponse> networkCall = ServiceCall.getNetWorkService().getProfile();
        networkCall.enqueue(new CustomCallback<ListDataResponse>(new CustomCallback.CustomCall() {
            @Override
            public void onSuccess(Response response) {
                ListDataResponse responseData = (ListDataResponse) response.body();
                if (listDataListener != null) {
                    listDataListener.onCompleteLoading(responseData);
                }
            }

            @Override
            public void onError(NetworkErrorParser networkError) {
                if (listDataListener != null) {
                    listDataListener.onError(networkError.getNetworkError().getMessage());
                }
            }
        }));
    }
}
