package Factory;

/**
 * 工厂模式测试类
 *
 * @author: bill
 * @create: 2024/06/29 14:44
 */
public class Test {

    public static void main(String[] args) {
        AnimalFactory animalFactory = new BlackCatInstanceFactory();
        Cat cat = animalFactory.createCat();
        cat.eat();
    }

}
