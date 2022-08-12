package api;

import models.User;
import retrofit2.Call;
import retrofit2.http.*;

public interface UsersService {
    @GET("users/{id}")
    @Headers("Content-Type: application/json")
    Call<User> getUser(@Path("id") int id);

    @POST("users")
    Call<User> postUser(@Body User user);
}