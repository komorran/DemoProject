package posts;

import abstractions.AbstractTest;
import models.Post;
import models.factories.UsersFactory;
import org.junit.jupiter.api.Test;
import posts.modules.PostsTestModule;
import users.UsersTestsContext;

import javax.inject.Inject;
import java.io.IOException;

public class PostsTests extends AbstractTest<PostsTestModule> {

    @Inject
    private PostsTestContext postsTestContext;

    @Inject
    private UsersTestsContext usersTestsContext;

    @Override
    protected PostsTestModule getModule() {
        return new PostsTestModule();
    }

    @Test
    public void createPostTest() throws IOException {
        var user = usersTestsContext.postUser(UsersFactory.getUser())
                .getResponse()
                .body();

        var post = Post.builder()
                .userId(user.getId())
                .title("qweqweqwe")
                .body("qqqqqqqqqq")
                .build();

        var postResponse = postsTestContext.createPost(post)
                .assertSuccessful()
                .assertResponseBodyIsNotNull()
                .assertPost(post)
                .getResponseBody();

        var getResponse = postsTestContext.getPostById(postResponse.getId())
                .assertSuccessful()
                .assertResponseBodyIsNotNull()
                .assertPost(post);
    }
}
