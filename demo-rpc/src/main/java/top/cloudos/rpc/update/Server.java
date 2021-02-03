package top.cloudos.rpc.update;

import java.io.IOException;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/18 15:29
 * @description
 **/
public interface Server {
    public void stop();

    public void start() throws IOException;

    public void register(Class serviceInterface, Class impl);

    public boolean isRunning();

    public int getPort();
}
