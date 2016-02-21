package launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Created by gleb on 2/12/16.
 */
@SpringBootApplication(scanBasePackages = {"websocket", "service"})
@EnableDiscoveryClient
public class WebServer {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.setProperty("spring.application.name", args[0].toLowerCase());
            System.setProperty("server.port", args[1]);
        }
        SpringApplication.run(WebServer.class, args);
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
