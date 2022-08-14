package demo.models.infrastructure.headers;

import lombok.Value;

@Value
public class Header {
    String name;

    String value;

    public static Header create(String name, String value) {
        return new Header(name, value);
    }
}
