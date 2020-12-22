package travel.configuration;

public interface RedisPrefixKey {
    Long getExpireSeconds();


    String getPrefixName();
}
