package posts;

import abstractions.AbstractTest;
import models.Post;
import models.factories.UsersFactory;
import org.junit.jupiter.api.Test;
import posts.modules.PostsTestsModule;
import users.UsersTestsSteps;

import javax.inject.Inject;
import java.io.IOException;

public class PostsTests extends AbstractTest<PostsTestsModule, PostsTestSteps> {

    @Inject
    private UsersTestsSteps usersTestsSteps;

    @Override
    protected PostsTestsModule getModule() {
        return new PostsTestsModule();
    }

    @Test
    public void createPostTest() throws IOException {
        var user = usersTestsSteps.postUser(UsersFactory.getUser())
                .getResponse()
                .body();

        var post = Post.builder()
                .userId(user.getId())
                .title("qweqweqwe")
                .body("qqqqqqqqqq")
                .build();

        var postResponse = testSteps.createPost(post)
                .assertSuccessful()
                .assertResponseBodyIsNotNull()
                .assertPost(post)
                .getResponse()
                .body();

        var getResponse = testSteps.getPostById(postResponse.getId())
                .assertSuccessful()
                .assertResponseBodyIsNotNull()
                .assertPost(post);
    }
}
