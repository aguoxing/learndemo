package top.cloudos.patterns.factory;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/2 17:08
 * @description
 **/
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
