package travel.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RedisLock {
    private static Logger LOG = LoggerFactory.getLogger(RedisLock.class);
    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     *
     * @param key
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key,String value){
        if(redisTemplate.opsForValue().setIfAbsent(key, value)){
            return true;
        }
        String currentValue = redisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(currentValue)&&
        Long.parseLong(currentValue)<System.currentTimeMillis()){
            String oldValue = redisTemplate.opsForValue().getAndSet(key,value);
            if(!StringUtils.isEmpty(oldValue)&&oldValue.equals(currentValue)){
                return true;
            }
        }
        return false;
    }
    public void unlock(String key,String value){
       try{
           String currentValue =  redisTemplate.opsForValue().get(key);
           if(!StringUtils.isEmpty(currentValue)&&currentValue.equals(value)){
               redisTemplate.opsForValue().getOperations().delete(key);
           }
       }catch (Exception e){
           LOG.error("[redis分布式锁]解锁失败！",e);
       }
    }
}
