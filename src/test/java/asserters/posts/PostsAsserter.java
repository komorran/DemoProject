package asserters.posts;

import asserters.core.HttpResponseAsserter;
import io.qameta.allure.Step;
import lombok.Builder;
import models.Post;
import retrofit2.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostsAsserter extends HttpResponseAsserter<PostsAsserter, Post> {
    @Builder
    public PostsAsserter(Response<Post> response) {
        super(response);
    }

    @Step("Assert the post")
    public PostsAsserter assertPost(Post expected) {
        var post = getResponseBody();

        assertEquals(expected.getUserId(), post.getUserId());
        assertEquals(expected.getTitle(), post.getTitle());
        assertEquals(expected.getBody(), post.getBody());

        return this;
    }
}
