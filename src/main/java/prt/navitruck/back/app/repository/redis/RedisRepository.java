package prt.navitruck.back.app.repository.redis;

import java.util.Map;

public interface RedisRepository {

    public void save(String str, String id);

    public String findById(String id);

    public Map<String, String> findAll();
}
