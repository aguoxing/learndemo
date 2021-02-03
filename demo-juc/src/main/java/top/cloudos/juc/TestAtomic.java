package top.cloudos.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/21 14:44
 * @description 原子性 CAS
 * 原子变量：jdk1.5后java.util.concurrent.atomic包下提供了常用的原子变量：
 * 1.volatile保证内存可见性
 * 2.CAS（Compare And Swap）算法保证数据的原子性
 * CAS算法是硬件对于并发操作共享数据的支持
 * CAS包含3个操作数：
 * 内存值 V
 * 预估值 A
 * 更新值 B
 * 当且仅当V == A，V = B，否则不做任何操作
 **/
public class TestAtomic {
    public static void main(String[] args) {
        AtomicDemo atomicDemo = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(atomicDemo).start();
        }
    }
}

class AtomicDemo implements Runnable {
    private AtomicInteger a = new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + getA());
    }

    public int getA() {
        return a.getAndIncrement();
    }
}
