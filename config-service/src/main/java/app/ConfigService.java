package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by gleb on 2/19/16.
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigService {
    public static void main(String[] args) {
        SpringApplication.run(ConfigService.class, args);
    }
}
