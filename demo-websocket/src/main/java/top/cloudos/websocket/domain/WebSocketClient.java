package top.cloudos.websocket.domain;

import javax.websocket.Session;
import java.io.Serializable;

/**
 * @author gx
 * @date 2021/9/19 14:04
 */
public class WebSocketClient implements Serializable {
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 连接的uri
     */
    private String uri;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
