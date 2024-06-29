package Single;

/**
 * 单例模式：volatile+双重锁模型
 * 该模式线程安全
 * 利用关键字volatile来禁止对实例化过程中的指令重排 从而实现线程安全
 *
 * @author: bill
 * @create: 2024/06/29 13:45
 */
public class SingletonExample5 {


    SingletonExample5() {
    }


    private volatile static SingletonExample5 instance = null;

    public SingletonExample5 getInstance() {
        if (instance == null) {
            synchronized (SingletonExample5.class) {
                instance = new SingletonExample5();
            }
        }
        return instance;
    }


}
