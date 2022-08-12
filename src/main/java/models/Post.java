package models;

import com.google.gson.annotations.SerializedName;
import lombok.Value;

@Value
public class Post {
    int id;

    @SerializedName("user_id")
    int userId;

    String title;

    String body;
}
