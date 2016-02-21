package launcher;


import org.springframework.boot.SpringApplication;

import java.util.Arrays;

/**
 * Created by gleb on 2/12/16.
 */
public class Main {

    public static void main(String[] args) {

        String serverName;

        switch (args.length) {
            case 2:
                System.setProperty("server.port", args[1]);
            case 1:
                serverName = args[0].toLowerCase();
                break;
            default:
                System.out.println("Unknown params: " + Arrays.toString(args));
                return;
        }

        switch (serverName) {
            case "registration":
                SpringApplication.run(EurekaService.class, args);
                break;
            case "core":
                SpringApplication.run(CoreServer.class, args);
                break;
            case "web":
                SpringApplication.run(WebServer.class, args);
                break;
            default:
                System.out.println("Unknown service type: " + serverName);
                break;
        }
    }
}
