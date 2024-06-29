package Prototype;

import BuildFactory.Man;

/**
 * 原型模型测试类
 *
 * @author: bill
 * @create: 2024/06/29 15:46
 */
public class Test {

    public static void main(String[] args) {
        ProtoType protoType = new ConCreateProtoType("test");
        ProtoType protoType2 = (ProtoType) protoType.clone();
        System.out.println(protoType.getName());
        System.out.println(protoType2.getName());
    }

}
