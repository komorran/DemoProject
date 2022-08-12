package api;

import models.Post;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostsService {
    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") int id);

    @POST("posts")
    Call<Post> createPost(@Body Post post);
}
