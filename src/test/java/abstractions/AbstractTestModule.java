package abstractions;

import api.interceptors.BearerInterceptor;
import com.google.inject.AbstractModule;
import config.AppConfig;
import io.qameta.allure.okhttp3.AllureOkHttp3;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class AbstractTestModule extends AbstractModule {
    protected Retrofit retrofit;

    protected AbstractTestModule() {
        var client = configureOkHttpClientBuilder()
                .build();

        this.retrofit = configureRetrofitBuilder()
                .client(client)
                .build();
    }

    protected OkHttpClient.Builder configureOkHttpClientBuilder() {
        return new OkHttpClient.Builder()
                .addInterceptor(new AllureOkHttp3())
                .addInterceptor(new BearerInterceptor());
    }

    protected Retrofit.Builder configureRetrofitBuilder() {
        return new Retrofit.Builder()
                .baseUrl(AppConfig.baseUrl)
                .addConverterFactory(GsonConverterFactory.create());
    }
}
