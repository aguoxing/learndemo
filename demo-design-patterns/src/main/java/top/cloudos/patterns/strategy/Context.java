package top.cloudos.patterns.strategy;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/2 17:04
 * @description
 **/
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}
