package modules;

import api.UsersService;
import asserters.users.UserAsserter;

public class UsersTestModule extends AbstractTestModule {

    @Override
    protected void configure() {
        var usersService = retrofit.create(UsersService.class);

        bind(UsersService.class).toInstance(usersService);
        bind(UserAsserter.UserAsserterBuilder.class).toInstance(UserAsserter.builder());
    }
}
