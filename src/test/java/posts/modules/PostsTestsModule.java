package posts.modules;

import abstractions.AbstractTestModule;
import api.PostsService;
import api.UsersService;
import asserters.core.HttpResponseAsserter;
import asserters.posts.PostsAsserter;

public class PostsTestsModule extends AbstractTestModule {
    @Override
    protected void configure() {
        var postsService = retrofit.create(PostsService.class);
        var usersService = retrofit.create(UsersService.class);

        bind(PostsService.class).toInstance(postsService);
        bind(UsersService.class).toInstance(usersService);
        bind(HttpResponseAsserter.class).to(PostsAsserter.class);
    }
}
