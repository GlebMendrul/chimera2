package websocket;

import core.InputData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ChimeraService;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by gleb on 2/12/16.
 */
@Component
@ServerEndpoint("/websocket")
public class ChimeraWebSocket {

    @Autowired
    private ChimeraService service;

    @OnMessage
    public void onMessage(String input, Session session) {
        new Thread(() -> {
            InputData inputData = createInputData(input);
            service.init(inputData);
            while (service.hasNext()) {
                String value = service.next();
                if (value != null) {
                    if (!session.isOpen()) return;
                    session.getAsyncRemote().sendText(value);
                }
            }
        }).start();
    }

    private InputData createInputData(String input) {
        String[] params = input.split("_");
        String fileName = params[0];
        String type = params[1];
        Integer from = Integer.valueOf(params[2]);
        Integer to = Integer.valueOf(params[3]);

        return new InputData(fileName, type, from, to);
    }
}
