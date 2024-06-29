package Factory;

/**
 * @author: bill
 * @create: 2024/06/29 14:39
 */
public class BlackCat implements Cat{
    @Override
    public void eat() {
        System.out.printf("black cat is eating");
    }
}
