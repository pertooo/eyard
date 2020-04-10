package prt.navitruck.back.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import prt.navitruck.back.app.jwt.TokenAuthenticationService;
import prt.navitruck.back.app.service.RedisService;

@SpringBootApplication
public class EyardApplication {

	@Autowired
	private RedisService redisService;

	@Value("${ENC_KEY}")
	private String encKey;

	@Bean
	public TokenAuthenticationService tokenAuthService() {
		return new TokenAuthenticationService(redisService,encKey);
	}

	@Bean
	public ObjectMapper mapper(){
		return new ObjectMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(EyardApplication.class, args);

	}

}
