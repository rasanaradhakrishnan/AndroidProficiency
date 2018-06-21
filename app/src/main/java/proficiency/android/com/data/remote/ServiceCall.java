package proficiency.android.com.data.remote;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import proficiency.android.com.ui.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;



public class ServiceCall {

    private static final String LOG_TAG = "ServiceCall";

    public static String readFromAsset(Context context, String url){
        String json = null;
        try {
            InputStream is = context.getAssets().open(url + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            int read = is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Log.d(LOG_TAG, String.valueOf(read));
        } catch (IOException e) {
            Log.d(LOG_TAG, e.getMessage(), e);
        }
        return json;
    }

    public static INetworkService getNetWorkService() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        InfyEComInterceptor eComInterceptor = new InfyEComInterceptor();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …
        httpClient.addInterceptor(eComInterceptor);
        // add logging as last interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        return retrofit.create(INetworkService.class);
    }

    /**
     * Interceptor to Add Request Headers depending on request type and user type
     */
    private static class InfyEComInterceptor implements Interceptor {

        public InfyEComInterceptor() {
        }

        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request original = chain.request();

            // Customize the request
            Request request = original.newBuilder()
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .addHeader("token", "")
                    .method(original.method(), original.body())
                    .build();
            // Customize or return the response
            return chain.proceed(request);
        }
    }
}
