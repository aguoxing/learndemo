package top.cloudos.juc;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/21 14:34
 * @description volatile
 * volatile关键字：当多个线程操作共享数据时，可以保证内存中的数据可见
 * 与synchronized相比，是一种轻量级的同步策略
 * <p>
 * 1.volatile不具备“互斥性”
 * 2.volatile不能保证变量的原子性
 **/
public class TestVolatile {
    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();

        while (true) {
            if (td.isFlag()) {
                System.out.println("======");
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable {
    private boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
