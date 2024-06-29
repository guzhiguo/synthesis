package BuildFactory;

/**
 * 抽象工厂
 *
 * @author: bill
 * @create: 2024/06/29 14:59
 */
public interface PersonBuilder {
    void buildHeard();

    void buildBody();

    void buildFoot();

    Person buildPerson();

}


