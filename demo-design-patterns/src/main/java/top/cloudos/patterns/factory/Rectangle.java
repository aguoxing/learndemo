package top.cloudos.patterns.factory;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/2 17:07
 * @description
 **/
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
