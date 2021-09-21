package top.cloudos.websocket.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.cloudos.websocket.service.WebSocketServer;

import java.util.Map;

/**
 * @author gx
 * @date 2021/9/20 18:09
 */
@RestController
@RequestMapping("/websocket")
public class WebSocketController {

    @PostMapping("/push")
    public void sendMessage(@RequestBody Map<String, String> map) {
        if (map.get("name") == null) {
            WebSocketServer.sendPublicMessage(map.get("message"));
        } else {
            WebSocketServer.sendMessage(map.get("name"), map.get("message"));
        }
    }
}
