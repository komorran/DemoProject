package posts;

import abstractions.AbstractTestSteps;
import api.PostsService;
import asserters.posts.PostsAsserter;
import io.qameta.allure.Step;
import models.Post;

import javax.inject.Inject;
import java.io.IOException;

public class PostsTestSteps extends AbstractTestSteps {
    private final PostsService postsService;
    private PostsAsserter postsAsserter;

    @Inject
    public PostsTestSteps(PostsService postsService, PostsAsserter postsAsserter) {
        this.postsService = postsService;
        this.postsAsserter = postsAsserter;
    }

    @Step("Get a post by ID: {id}")
    public PostsAsserter getPostById(int id) throws IOException {
        var response = postsService.getPost(id).execute();
        return postsAsserter.useResponse(response);
    }

    @Step("Create a post")
    public PostsAsserter createPost(Post post) throws IOException {
        var response = postsService.createPost(post).execute();
        return postsAsserter.useResponse(response);
    }
}
