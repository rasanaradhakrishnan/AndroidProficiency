package proficiency.android.com.data.remote;


import proficiency.android.com.data.model.api.ListDataResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface INetworkService {

    @GET(ApiEndPoint.ENDPOINT_DROPBOX)
    Call<ListDataResponse> getProfile();
}
