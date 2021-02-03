package top.cloudos.rpc.update;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/18 15:28
 * @description
 **/
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHi(String name) {
        return "hello" + name;
    }
}
