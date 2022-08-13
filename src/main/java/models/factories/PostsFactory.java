package models.factories;

import com.github.javafaker.Faker;
import models.Post;

public class PostsFactory {
    private static final Faker faker = new Faker();

    public static Post getPost(int userId) {
        return Post.builder()
                .userId(userId)
                .title(faker.name().title())
                .body(faker.name().title())
                .build();
    }
}
