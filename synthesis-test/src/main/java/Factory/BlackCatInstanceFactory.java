package Factory;

/**
 * @author: bill
 * @create: 2024/06/29 14:40
 */
public class BlackCatInstanceFactory implements AnimalFactory {


    @Override
    public Cat createCat() {
        return new BlackCat();
    }
}
