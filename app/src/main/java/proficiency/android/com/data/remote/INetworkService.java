package proficiency.android.com.data.remote;


import proficiency.android.com.data.model.api.ListDataResponse;
import retrofit2.http.GET;
import rx.Observable;

public interface INetworkService {

    @GET(ApiEndPoint.ENDPOINT_DROPBOX)
    Observable<ListDataResponse> getProfile();
}
