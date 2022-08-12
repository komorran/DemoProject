package api;

import models.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostsService {
    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") int id);
}
