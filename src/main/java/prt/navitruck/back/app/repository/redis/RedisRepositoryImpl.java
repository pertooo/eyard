package prt.navitruck.back.app.repository.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class RedisRepositoryImpl implements RedisRepository {

    private RedisTemplate<String, String> redisTemplate;

    private HashOperations hashOperations;

    public RedisRepositoryImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;

        hashOperations = redisTemplate.opsForHash();
    }


    public void save(String str, String id) {
        hashOperations.put("USER", id, str);
    }

    public String findById(String id) {
        return (String) hashOperations.get("USER", id);
    }

    public Map<String, String> findAll() {
        return hashOperations.entries("USER");
    }
}
