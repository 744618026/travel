package travel.configuration;

public class RedisBasePrefix implements RedisPrefixKey{
    private Long expireSeconds;

    private String prefixName;

    public RedisBasePrefix(Long expireSeconds, String prefixName) {
        this.expireSeconds = expireSeconds;
        this.prefixName = prefixName;
    }
    public RedisBasePrefix(String prefixName) {
        this.prefixName = prefixName;
    }
    @Override
    public Long getExpireSeconds() {
        return this.expireSeconds;
    }

    @Override
    public String getPrefixName() {
        return this.prefixName.concat(":");
    }
}
