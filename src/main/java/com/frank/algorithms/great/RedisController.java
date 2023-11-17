package com.frank.algorithms.great;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.core.types.Expiration;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @program: algorithms
 * @description:
 * @author: Francis-Tmac
 * @create: 2020-12-20
 **/
@Slf4j
public class RedisController {

    @Autowired
    private RedisTemplate redisTemp;

    public static void main(String[] args) {

        Singleton singleton = Singleton.getInstance();
    }

    public String redisLock(){
        final String key = "redisKey";
        final String value = UUID.randomUUID().toString();

        RedisCallback<Boolean> redisCallback = connection->{
            // 设置NX
            RedisStringCommands.SetOption setOption = RedisStringCommands.SetOption.ifAbsent();
            // 设置过期时间
            Expiration expiration = Expiration.seconds(30);
            // 序列化key 和 value
            byte[] redisKey = redisTemp.getKeySerializer().serialize(key);
            byte[] redisValue = redisTemp.getHashValueSerializer().serialize(value);
            // 执行 setNx 操作
            Boolean result = connection.set(redisKey,redisValue,expiration,setOption);

            return result;
        };

        Boolean lock = (Boolean) redisTemp.execute(redisCallback);
        if(lock){
//            log.info("进入锁！！！");
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then\n" +
                        "    return redis.call(\"del\",KEYS[1])\n" +
                        "else\n" +
                        "    return 0\n" +
                        "end";
                RedisScript<Boolean> redisScript = RedisScript.of(script, Boolean.class);
                List<String> keyList = Arrays.asList(key);
                Boolean result = (Boolean) redisTemp.execute(redisScript, keyList, value);
//                log.info("释放锁的结果：{}", result);
            }
        }
        return "finish method";

    }



}
