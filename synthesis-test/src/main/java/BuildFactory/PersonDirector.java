package BuildFactory;

/**
 * Person对象的整体构造器
 *
 * @author: bill
 * @create: 2024/06/29 15:06
 */
public class PersonDirector {

    public Person constructPerson(PersonBuilder personBuilder) {
        personBuilder.buildHeard();
        personBuilder.buildBody();
        personBuilder.buildFoot();
        return personBuilder.buildPerson();
    }

}
