package asserters.posts;

import asserters.core.HttpResponseAsserter;
import io.qameta.allure.Step;
import models.Post;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostsAsserter extends HttpResponseAsserter<PostsAsserter, Post> {
    @Step("Assert a post")
    public PostsAsserter assertPost(Post expected) {
        var post = response.body();

        assertEquals(expected.getUserId(), post.getUserId());
        assertEquals(expected.getTitle(), post.getTitle());
        assertEquals(expected.getBody(), post.getBody());

        return this;
    }
}
