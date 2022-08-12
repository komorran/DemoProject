package asserters.users;

import models.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAsserter {
    public static void assertUser(User expected, User actual) {
        //assertEquals(expected.getId(), actual.getId(), "User IDs should be equal");
        assertEquals(expected.getName(), actual.getName(), "User names should be equal");
        assertEquals(expected.getEmail(), actual.getEmail(), "User emails should be equal");
        assertEquals(expected.getGender(), actual.getGender(), "User genders should be equal");
        assertEquals(expected.getStatus(), actual.getStatus(), "User statuses should be equal");
    }
}
