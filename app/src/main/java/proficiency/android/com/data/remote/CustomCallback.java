package proficiency.android.com.data.remote;

import proficiency.android.com.data.errors.NetworkErrorParser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This is the custom callback. All the necessary network response handling should be done.
 * Currently network errors are handled here. The network error handling is done using a common error module.
 * two kinds can come. One is generic network errorrs and the other is the server responding errors mainly IOExceptions.
 * The second comes when request is cancelled.
 */
public class CustomCallback <Object> implements Callback<Object> {
    private CustomCall mCustomCall;

    public CustomCallback(CustomCall customCall) {
        mCustomCall = customCall;
    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        if (response.isSuccessful()) {
            mCustomCall.onSuccess(response);
        } else {
            mCustomCall.onError(new NetworkErrorParser(response));
        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        mCustomCall.onError(new NetworkErrorParser(t));
    }

    public interface CustomCall {
        void onSuccess(Response response);

        void onError(NetworkErrorParser networkError);
    }
}
