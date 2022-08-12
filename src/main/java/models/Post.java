package models;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Post {
    int id;

    @SerializedName("user_id")
    int userId;

    String title;

    String body;
}
