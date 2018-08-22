package com.ucs.csxbank.websit.wap.util;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class RedisCounterUtils {
    private static StringRedisTemplate redisTemplate;
    static {
        redisTemplate = SpringUtils.getBean(StringRedisTemplate.class);
    }
    public static Long getCount(Counter counter, String hashKey) throws Exception {
        expireKey(counter.getName(),counter.getExpireTime());
        Map<Object, Object> counterMap = redisTemplate.opsForHash().entries(counter.getName());
        if (counterMap.isEmpty()){
            return 0L;}
        if(!counterMap.containsKey(hashKey)) {
            return 0L;
        }
        return Long.parseLong(counterMap.get(hashKey).toString());
    }

    public static long incrementCount(Counter counter, String hashKey) throws Exception {
        long count = redisTemplate.opsForHash().increment(counter.getName(), hashKey, 1);
        expireKey(counter.getName(),counter.getExpireTime());
        return count;
    }

    public static void resetCount(Counter counter, String hashKey) {
        redisTemplate.opsForHash().delete(counter.getName(), hashKey);
    }

    private static void expireKey(String key,Date expireTime) throws Exception {
        redisTemplate.expireAt(key, expireTime);
    }
}
