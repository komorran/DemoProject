package api.interceptors;

import models.infrastructure.config.AppConfig;
import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;
import java.util.Base64;

public class BearerInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        var newRequest=chain.request().newBuilder()
                .header("Authorization","Bearer "+ new String(Base64.getDecoder().decode(AppConfig.apiKey)))
                .build();

        return chain.proceed(newRequest);
    }
}
