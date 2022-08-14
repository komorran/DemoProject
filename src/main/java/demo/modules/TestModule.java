package demo.modules;

import com.google.inject.AbstractModule;
import demo.api.PostsService;
import demo.api.UsersService;
import demo.api.interceptors.BearerInterceptor;
import demo.asserters.posts.PostsAsserter;
import demo.asserters.users.UserAsserter;
import demo.models.infrastructure.config.AppConfig;
import io.qameta.allure.okhttp3.AllureOkHttp3;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestModule extends AbstractModule {
    private final Retrofit retrofit;

    public TestModule() {
        var client = configureOkHttpClientBuilder()
                .build();

        this.retrofit = configureRetrofitBuilder()
                .client(client)
                .build();
    }

    @Override
    protected void configure() {
        configureServices();
        configureAsserters();
    }

    private void configureServices() {
        var postsService = retrofit.create(PostsService.class);
        var usersService = retrofit.create(UsersService.class);

        bind(PostsService.class).toInstance(postsService);
        bind(UsersService.class).toInstance(usersService);
    }

    private void configureAsserters() {
        bind(UserAsserter.UserAsserterBuilder.class).toInstance(UserAsserter.builder());
        bind(PostsAsserter.PostsAsserterBuilder.class).toInstance(PostsAsserter.builder());
    }

    private OkHttpClient.Builder configureOkHttpClientBuilder() {
        return new OkHttpClient.Builder()
                .addInterceptor(new AllureOkHttp3())
                .addInterceptor(new BearerInterceptor());
    }

    private Retrofit.Builder configureRetrofitBuilder() {
        return new Retrofit.Builder()
                .baseUrl(AppConfig.baseUrl)
                .addConverterFactory(GsonConverterFactory.create());
    }
}
