package users.modules;

import abstractions.AbstractTestModule;
import api.UsersService;

public class UsersTestsModule extends AbstractTestModule {

    @Override
    protected void configure() {
        var usersService = retrofit.create(UsersService.class);

        bind(UsersService.class).toInstance(usersService);
    }
}
