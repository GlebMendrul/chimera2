package websocket;

import constants.Compress;
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

    private static final String[] OSCILLATIONS_100 = {"100x100x100", "8"};
    private static final String[] OSCILLATIONS_200 = {"200x200x200", "64"};
    private static final String[] OSCILLATIONS_400 = {"400x400x400", "512"};

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

    private Compress getCompressValue(String compress, String fileName) {
        Compress compressEnum = Compress.getEnum(compress);
        if (compressEnum != null) {
            if (fileName.contains(OSCILLATIONS_100[0])) {
                compressEnum.setCompressValue(Integer.valueOf(OSCILLATIONS_100[1]));
            } else if (fileName.contains(OSCILLATIONS_200[0])) {
                compressEnum.setCompressValue(Integer.valueOf(OSCILLATIONS_200[1]));
            } else if (fileName.contains(OSCILLATIONS_400[0])) {
                compressEnum.setCompressValue(Integer.valueOf(OSCILLATIONS_400[1]));
            } else {
                compressEnum.setCompressValue(0);
            }
            return compressEnum;
        }
        return Compress.N;
    }

    private InputData createInputData(String input) {
        String[] params = input.split("_");
        String fileName = params[0];
        String type = params[1];
        String compress = params[2];
        Integer from = Integer.valueOf(params[2]);
        Integer to = Integer.valueOf(params[3]);

        return new InputData(fileName, type, from, to, getCompressValue(compress, fileName));
    }
}
