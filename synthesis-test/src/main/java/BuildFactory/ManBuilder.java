package BuildFactory;

/**
 * 构造器
 *
 * @author: bill
 * @create: 2024/06/29 15:01
 */
public class ManBuilder implements PersonBuilder {
    Person person;

    public ManBuilder() {
        this.person = new Man();
    }

    public ManBuilder(Person person) {
        this.person = person;
    }

    @Override
    public void buildHeard() {
        System.out.println("生成了一个男人的头");
    }

    @Override
    public void buildBody() {
        System.out.println("生成了一个男人的身体");
    }

    @Override
    public void buildFoot() {
        System.out.println("生成了一个男人的脚");
    }

    @Override
    public Person buildPerson() {
        return person;
    }
}
