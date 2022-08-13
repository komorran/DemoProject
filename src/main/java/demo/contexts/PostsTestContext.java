package demo.contexts;

import demo.api.PostsService;
import demo.asserters.posts.PostsAsserter;
import demo.models.Post;
import io.qameta.allure.Step;
import retrofit2.Response;

import javax.inject.Inject;
import java.io.IOException;

public class PostsTestContext {
    private final PostsService postsService;
    private final PostsAsserter.PostsAsserterBuilder postsAsserterBuilder;

    @Inject
    public PostsTestContext(PostsService postsService, PostsAsserter.PostsAsserterBuilder postsAsserterBuilder) {
        this.postsService = postsService;
        this.postsAsserterBuilder = postsAsserterBuilder;
    }

    @Step("Get the post by ID: {id}")
    public PostsAsserter getPostById(int id) throws IOException {
        var response = postsService.getPost(id).execute();
        return buildAsserter(response);
    }

    @Step("Create the post")
    public PostsAsserter createPost(Post post) throws IOException {
        var response = postsService.createPost(post).execute();
        return buildAsserter(response);
    }

    private PostsAsserter buildAsserter(Response<Post> response) {
        return postsAsserterBuilder.response(response).build();
    }
}
