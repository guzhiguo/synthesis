package BuildFactory;

/**
 * 建造者模式测试类
 *
 * @author: bill
 * @create: 2024/06/29 15:13
 */
public class Test {
    public static void main(String[] args) {
        PersonDirector personDirector = new PersonDirector();
        Person person = personDirector.constructPerson(new ManBuilder());

        person.getHeard();
        person.getBody();
        person.getFoot();
    }
}
