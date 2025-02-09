package config.web;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:properties/${env}.properties",
        "classpath:properties/local.properties"

})
public interface WebConfig extends Config {
    @Key("browser")
    @DefaultValue("CHROME")
    String browser();

    @Key("browserVersion")
    @DefaultValue("125.0")
    String browserVersion();

    @Key("browserSize")
    String browserSize();

    @Key("baseUrl")
    String baseUrl();

    @Key("isRemote")
    boolean isRemote();

    @Key("remoteUrl")
    String remoteUrl();

    @Key("pageLoadStrategy")
    String pageLoadStrategy();
}