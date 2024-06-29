package Single;

/**
 * 饿汉模式
 * 该模式线程安全
 *
 * @author: bill
 * @create: 2024/06/29 14:07
 */
public class SingletonExample2 {

    SingletonExample2() {
    }

    private static SingletonExample2 instance = new SingletonExample2();

    public SingletonExample2 getInstance() {
        return instance;
    }
}
