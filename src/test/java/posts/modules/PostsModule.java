package posts.modules;

import abstractions.AbstractTestModule;
import api.PostsService;

public class PostsModule extends AbstractTestModule {
    @Override
    protected void configure() {
        var usersService = retrofit.create(PostsService.class);

        bind(PostsService.class).toInstance(usersService);
    }
}
