package Single;

/**
 * 单例模式：双重锁同步锁
 * 该模式非线程安全 原因如下
 * 当系统直行 instance = new Single.SingletonExample4()时，会执行如下命令
 * memory = allocate() 分配对象的存储空间
 * ctorInstance() 初始化对象
 * instance = memory; 设置instance指向刚分配的空间
 * 而当多线程情况下，以上指令发生重新排序后，线程A、B拿到的instance很有可能是还未初试化的对象，该实例一旦被使用就很有可能出现问题
 *
 * @author: bill
 * @create: 2024/06/29 13:51
 */
public class SingletonExample4 {

    SingletonExample4() {
    }

    private static SingletonExample4 instance = null;

    public SingletonExample4 getInstance() {
        if (instance == null) {
            synchronized (SingletonExample4.class) {
                instance = new SingletonExample4();
            }
        }
        return instance;
    }
}
