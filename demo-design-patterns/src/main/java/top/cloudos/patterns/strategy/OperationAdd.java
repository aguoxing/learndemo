package top.cloudos.patterns.strategy;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/2 17:02
 * @description
 **/
public class OperationAdd implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
