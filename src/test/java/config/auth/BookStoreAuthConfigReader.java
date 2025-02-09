package config.auth;

import org.aeonbits.owner.ConfigFactory;

public enum BookStoreAuthConfigReader {
    Instance;

    private static final AuthConfig authConfig =
            ConfigFactory.create(
                    AuthConfig.class,
                    System.getProperties()
            );

    public AuthConfig read() {
        return authConfig;
    }

}