package demo.models;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {
    int id;

    String name;

    String email;

    String gender;

    String status;
}
