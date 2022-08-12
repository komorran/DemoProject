package users.modules;

import abstractions.AbstractTestModule;
import api.UsersService;
import asserters.core.HttpResponseAsserter;
import asserters.users.UserAsserter;

public class UsersTestsModule extends AbstractTestModule {

    @Override
    protected void configure() {
        var usersService = retrofit.create(UsersService.class);

        bind(UsersService.class).toInstance(usersService);
        bind(HttpResponseAsserter.class).to(UserAsserter.class);
    }
}
