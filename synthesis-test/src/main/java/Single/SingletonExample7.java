package Single;

/**
 * 单例模式：通过枚举类实现单例模式
 * 该模式线程最安全
 *
 * @author: bill
 * @create: 2024/06/29 14:17
 */
public class SingletonExample7 {

    SingletonExample7() {
    }

    private static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton {
        INSTANCE;
        private SingletonExample7 singleton;

        //jvm保证这个方法绝对只调用一次
        Singleton() {
            singleton = new SingletonExample7();
        }

        private SingletonExample7 getSingleton() {
            return singleton;
        }
    }
}
