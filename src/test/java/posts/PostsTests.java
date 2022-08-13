package posts;

import abstractions.AbstractTest;
import contexts.PostsTestContext;
import models.factories.PostsFactory;
import models.factories.UsersFactory;
import org.junit.jupiter.api.Test;
import modules.PostsTestModule;
import contexts.UsersTestsContext;

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
