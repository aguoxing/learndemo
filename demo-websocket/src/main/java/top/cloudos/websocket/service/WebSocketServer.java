package top.cloudos.websocket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.cloudos.websocket.domain.WebSocketClient;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gx
 * @date 2021/9/19 13:34
 */
@ServerEndpoint(value = "/websocket/{userName}")
@Component
public class WebSocketServer {

    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static final AtomicInteger onlineCount = new AtomicInteger(0);

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
     */
    private static ConcurrentHashMap<String, WebSocketClient> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userName
     */
    private String userName = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userName") String userName) {
        if (!webSocketMap.containsKey(userName)) {
            // 在线数 +1
            addOnlineCount();
        }
        this.session = session;
        this.userName = userName;

        WebSocketClient client = new WebSocketClient();
        client.setSession(session);
        client.setUri(session.getRequestURI().toString());
        webSocketMap.put(userName, client);

        log.info("----------------------------------------------------------------------------");
        log.info("用户连接:" + userName + ",当前在线人数为:" + getOnlineCount());
        try {
            sendMessage("来自后台的反馈：连接成功");
        } catch (IOException e) {
            log.error("用户:" + userName + ",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketMap.remove(userName);
        subOnlineCount();
        log.info("----------------------------------------------------------------------------");
        log.info(userName + "用户退出,当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到用户消息:" + userName + ",报文:" + message);
        //可以群发消息
        //消息保存到数据库、redis
//        if (StringUtils.isNotBlank(message)) {
//
//        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.userName + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 连接服务器成功后主动推送
     */
    public void sendMessage(String message) throws IOException {
        synchronized (session) {
            this.session.getBasicRemote().sendText(message);
        }
    }

    /**
     * 群发自定义消息
     */
    public static void sendPublicMessage(String message) {
        log.info("推送消息到窗口");
        for (String key : webSocketMap.keySet()) {
            try {
                WebSocketClient webSocketClient = webSocketMap.get(key);
                if (webSocketClient != null) {
                    webSocketClient.getSession().getBasicRemote().sendText(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    /**
     * 向指定客户端发送消息
     *
     * @param userName
     * @param message
     */
    public static void sendMessage(String userName, String message) {
        try {
            WebSocketClient webSocketClient = webSocketMap.get(userName);
            if (webSocketClient != null) {
                webSocketClient.getSession().getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    public static synchronized int getOnlineCount() {
        return onlineCount.get();
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount.incrementAndGet();
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount.decrementAndGet();
    }

    public static ConcurrentHashMap<String, WebSocketClient> getWebSocketMap() {
        return webSocketMap;
    }

    public static void setWebSocketMap(ConcurrentHashMap<String, WebSocketClient> webSocketMap) {
        WebSocketServer.webSocketMap = webSocketMap;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
