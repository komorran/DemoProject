package demo.posts;

import demo.AbstractTest;
import demo.contexts.PostsApiContext;
import demo.models.factories.PostsFactory;
import demo.models.factories.UsersFactory;
import org.junit.jupiter.api.Test;
import demo.contexts.UsersApiContext;

import javax.inject.Inject;

import static io.qameta.allure.Allure.step;

public class PostsTests extends AbstractTest {

    @Inject
    private PostsApiContext postsApiContext;

    @Inject
    private UsersApiContext usersApiContext;

    @Test
    public void createPostTest() {
        var user = step("Precondition: Create an user", () -> usersApiContext.postUser(UsersFactory.getUser())
                .getResponseBody());

        var post = PostsFactory.getPost(user.getId());

        var postResponse = step("Create the post", () -> postsApiContext.createPost(post)
                .assertSuccessful()
                .assertResponseBodyIsNotNull()
                .assertPost(post)
                .getResponseBody());

        var getResponse = step("Get the post", () -> postsApiContext.getPostById(postResponse.getId())
                .assertSuccessful()
                .assertResponseBodyIsNotNull()
                .assertPost(post));
    }
}
