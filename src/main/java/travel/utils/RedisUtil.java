package travel.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import travel.configuration.RedisBasePrefix;
import travel.configuration.RedisPrefixKey;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisUtil {
    public static boolean set(RedisTemplate redisTemplate, RedisBasePrefix prefix,String key,Object value){
        try {
            long time = prefix.getExpireSeconds()==null?0:prefix.getExpireSeconds();
            if(time>0){
                redisTemplate.opsForValue().set(prefix.getPrefixName().concat(key),value,time, TimeUnit.SECONDS);
            }else{
                redisTemplate.opsForValue().set(prefix.getPrefixName().concat(key),value);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static Object get(RedisPrefixKey prefix, String key,RedisTemplate redisTemplate) {
        return key == null ? null : redisTemplate.opsForValue().get(prefix.getPrefixName().concat(key));
    }
    public static boolean delete(RedisPrefixKey prefix, String key,RedisTemplate redisTemplate){
        return redisTemplate.delete(prefix.getPrefixName().concat(key));
    }
    public static boolean deleteByPrefix(RedisPrefixKey prefix,RedisTemplate redisTemplate){
        Set<String> keys = redisTemplate.keys(prefix.getPrefixName().concat("*"));
        redisTemplate.delete(keys);
        return true;
    }
}
