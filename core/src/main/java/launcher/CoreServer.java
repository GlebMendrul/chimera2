package launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by gleb on 2/12/16.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CoreServer {

    public static void main(String[] args) throws Exception {
        System.setProperty("spring.config.name", "core-server");
        SpringApplication.run(CoreServer.class, args);
    }
}
