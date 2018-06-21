package proficiency.android.com.data;


import proficiency.android.com.data.model.api.ListDataResponse;

public interface ListDataListener {


    void onCompleteLoading(ListDataResponse listDataResponse);

    void onError(String message);
}
