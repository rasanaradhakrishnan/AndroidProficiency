package proficiency.android.com.data.errors;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * * This is error class for all network errors
 * Created by rasana_r on 3/9/2017.
 */
public class NetworkErrorParser {

    private static final String LOG_TAG = "NetworkErrorParser";
    private NetworkError networkError;

    public NetworkErrorParser(Response responseBody) {
        networkError = parseErrorMessage(responseBody);
    }

    public NetworkErrorParser(Throwable throwable) {
        networkError = new NetworkError();
        networkError.setMessage(throwable.getMessage());
    }

    private NetworkError parseErrorMessage(Response response) {
        NetworkError networkError= new NetworkError();
        if (response != null) {
            Response<ResponseBody> body = (Response<ResponseBody>) response;
            networkError.setStatusCode(body.code());
            networkError.setMessage(body.message());
        }else{
            networkError.setMessage("Generic error");
        }
        return networkError;
    }

    public NetworkError getNetworkError() {
        return networkError;
    }
}
