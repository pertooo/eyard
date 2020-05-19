package prt.navitruck.back.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.mail.imap.IdleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import prt.navitruck.back.app.jwt.TokenAuthenticationService;
import prt.navitruck.back.app.mail.EmailReceiver;
import prt.navitruck.back.app.service.redis.RedisService;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.mail.*;
import javax.mail.event.MessageCountAdapter;
import javax.mail.event.MessageCountEvent;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class EyardApplication {

	@Autowired
	private RedisService redisService;

	@Value("${ENC_KEY}")
	private String encKey;

	static String protocol = "imap";
	static String host = "imap.gmail.com";
	static String port = "993";

	static String userName = "navitruck2020@gmail.com";
	static String password = "Navitruck@2020";


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

//
//		EmailReceiver receiver = new EmailReceiver();
//		receiver.setMailReceiveListener(protocol, host, port, userName, password);

	}

}
