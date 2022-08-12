package api.interceptors;

import config.AppConfig;
import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;

public class BearerInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        var newRequest=chain.request().newBuilder()
                .header("Authorization","Bearer "+ AppConfig.apiKey)
                .build();

        return chain.proceed(newRequest);
    }
}
