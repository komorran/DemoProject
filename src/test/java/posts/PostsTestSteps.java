package posts;

import abstractions.AbstractTestSteps;
import api.PostsService;
import io.qameta.allure.Step;
import models.Post;
import retrofit2.Response;

import javax.inject.Inject;
import java.io.IOException;

public class PostsTestSteps extends AbstractTestSteps {
    private final PostsService postsService;

    @Inject
    public PostsTestSteps(PostsService postsService) {
        this.postsService = postsService;
    }

    @Step("Get a post by ID: {id}")
    public Response<Post> getPostById(int id) throws IOException {
        return postsService.getPost(id).execute();
    }
}
