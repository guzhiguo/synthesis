package Single;

/**
 * 单例模式：synchronized模式
 * 该模式线程安全 但是写法不推荐使用
 *
 * @author: bill
 * @create: 2024/06/29 14:02
 */
public class SingletonExample3 {

    SingletonExample3() {
    }

    private static SingletonExample3 instance = null;

    public synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }

}
