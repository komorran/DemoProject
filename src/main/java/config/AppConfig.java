package config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class AppConfig {
    private static Config config = ConfigFactory.load();

    public static final String baseUrl = config.getString("base.url");

    public static final String apiKey = config.getString("api.key");

    private AppConfig() {}
}
