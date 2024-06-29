package Single;

/**
 * 饿汉模式
 * 该模式线程安全
 * 在类装载时通过static进行创建 是系统安全的
 *
 * @author: bill
 * @create: 2024/06/29 14:13
 */
public class SingletonExample6 {

    SingletonExample6() {
    }

    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    public SingletonExample6 getInstance() {
        return instance;
    }
}
