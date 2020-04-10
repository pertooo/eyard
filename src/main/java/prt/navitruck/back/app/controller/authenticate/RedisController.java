package prt.navitruck.back.app.controller.authenticate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import prt.navitruck.back.app.model.domain.AuthenticationTokenImpl;
import prt.navitruck.back.app.repository.redis.RedisRepositoryImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisRepositoryImpl testRepository;

    @GetMapping("/add/{id}/{name}")
    @CrossOrigin(origins = "http://192.168.100.100:9090")
    public String add(@PathVariable("id") final String id,
                      @PathVariable("name") final String name) {
        testRepository.save(name, id);

        return testRepository.findById(id);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "http://192.168.100.100:9090")
    public Map<String, String> all(AuthenticationTokenImpl auth, HttpServletResponse response) {
   //     System.out.println(auth.getHash());

        return testRepository.findAll();
    }



}
