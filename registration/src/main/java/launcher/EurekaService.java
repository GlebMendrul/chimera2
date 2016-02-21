package launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by gleb on 2/12/16.
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaService {

    public static void main(String[] args) {
        SpringApplication.run(EurekaService.class, args);
    }
}

