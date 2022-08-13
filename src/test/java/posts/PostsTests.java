package posts;

import abstractions.AbstractTest;
import models.factories.PostsFactory;
import models.factories.UsersFactory;
import org.junit.jupiter.api.Test;
import posts.modules.PostsTestModule;
import users.UsersTestsContext;

import javax.inject.Inject;

import static io.qameta.allure.Allure.step;

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
    public void createPostTest() {
        var user = step("Precondition: Create an user", () -> usersTestsContext.postUser(UsersFactory.getUser())
                .getResponseBody());

        var post = PostsFactory.getPost(user.getId());

        var postResponse = step("Create the post", () -> postsTestContext.createPost(post)
                .assertSuccessful()
                .assertResponseBodyIsNotNull()
                .assertPost(post)
                .getResponseBody());

        var getResponse = step("Get the post", () -> postsTestContext.getPostById(postResponse.getId())
                .assertSuccessful()
                .assertResponseBodyIsNotNull()
                .assertPost(post));
    }
}
