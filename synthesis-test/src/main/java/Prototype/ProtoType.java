package Prototype;

/**
 * 原型模式: 通过实现Cloneable来创建原型类的父类
 *
 * @author: bill
 * @create: 2024/06/29 15:40
 */
public class ProtoType implements Cloneable {

    String name;

    public Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
