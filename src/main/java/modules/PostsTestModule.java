package modules;

import api.PostsService;
import api.UsersService;
import asserters.posts.PostsAsserter;

public class PostsTestModule extends AbstractTestModule {
    @Override
    protected void configure() {
        var postsService = retrofit.create(PostsService.class);
        var usersService = retrofit.create(UsersService.class);

        bind(PostsService.class).toInstance(postsService);
        bind(UsersService.class).toInstance(usersService);
        bind(PostsAsserter.PostsAsserterBuilder.class).toInstance(PostsAsserter.builder());
    }
}
