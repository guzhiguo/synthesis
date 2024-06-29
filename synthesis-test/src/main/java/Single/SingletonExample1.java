package Single;

/**
 * 饿汉模式
 * 该模式非线程安全 原因如下
 * 在多线程场景下，调用getInstance方法可能会同时创建多个对象
 *
 * @author: bill
 * @create: 2024/06/29 14:09
 */
public class SingletonExample1 {

    SingletonExample1() {
    }

    private static SingletonExample1 instance = null;

    public SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
