package demo.models.infrastructure.headers;

import lombok.Value;

@Value
public class Header {
    HeaderNames name;

    String value;

    public static Header create(HeaderNames name, String value) {
        return new Header(name, value);
    }

    public String getName() {
        return name.getName();
    }
}
